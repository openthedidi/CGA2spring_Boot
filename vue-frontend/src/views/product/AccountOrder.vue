<template>
  <Header />
  <main id="app">
    <!-- Content -->
    <div class="col-lg-8 col-xxl-9 mx-auto py-5">
      <h5 style="padding-left: 9%;">訂單搜尋</h5>
      <div class="shop-sidebar-block" style="width: 30%; margin-bottom: 20px; margin-left: 9%;">
        <input class="form-control" type="text" name="keysearch" id="keysearch" v-model="searchQuery" @input="keysearch" placeholder="請輸入訂單號碼或是遊戲名稱">
      </div>

      <div v-show="loading" style="display: flex; justify-content: center; align-items: center; min-height: 200px;">
        <div class="spinner-border text-info" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
        <h3 class="ms-3">訂單搜尋中</h3>
      </div>

      <section v-show="!loading" class="row mx-auto" id="content" name="content" style="background-color: white; width: 85%;">
        <!-- Orders List -->
        <div v-for="(item, index) in renderData[nowPage]" :key="index" class="table-responsive fs-md mb-4">
          <table class="table table-bordered table-hover mb-0">
            <thead class="text-700 bg-gray-200">
              <tr>
                <th class="fw-600" style="width:25%">訂單編號：點擊查看明細</th>
                <th class="fw-600" style="width:25%">下單日期</th>
                <th class="fw-600" style="width:25%">訂單狀態</th>
                <th class="fw-600" style="width:25%">金額</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="p-3">
                  <button class="btn btn-link text-decoration-none shadow-none" @click="item.openOrderDetail = !item.openOrderDetail">
                    {{item.orderNo}}
                  </button>
                </td>
                <td class="p-3">{{item.tranTime}}</td>
                <td class="p-3"><span class="badge bg-info m-0">{{item.orderStateStr}}</span></td>
                <td class="p-3">NT:{{item.orderTotalPrice}}</td>
              </tr>
            </tbody>
          </table>
          
          <!-- Order Details -->
          <div v-show="item.openOrderDetail" style="width: 100%;" class="mt-2">
            <table class="table table-bordered table-hover mb-0">
              <thead class="text-700 bg-gray-200">
                <tr>
                  <th class="fw-600" style="width:40%">遊戲名稱</th>
                  <th class="fw-600" style="width:15%">數量</th>
                  <th class="fw-600" style="width:15%">總價</th>
                  <th class="fw-600" style="width:30%">發表評論</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(orderDetails, dIndex) in item.orderDetail" :key="dIndex">
                  <td class="p-3">
                    <router-link :to="`/product/detail/${orderDetails.productNo}`">{{orderDetails.productName}}</router-link>
                  </td>
                  <td class="p-3">{{orderDetails.productSales}}</td>
                  <td class="p-3"><span class="badge bg-info m-0">{{orderDetails.productTotalPrice}}</span></td>
                  <td class="p-3">
                    <router-link v-if="item.orderState == 2 && orderDetails.CommentStar == 0" 
                                 :to="`/product/comment?ProductNo=${orderDetails.productNo}&OrderNo=${orderDetails.orderNo}`">我要發表遊戲評論</router-link>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </section>

      <!-- Pagination -->
      <nav class="d-flex justify-content-center mt-4" v-if="!loading && totalPage > 1">
        <ul class="pagination" id="pageid">
          <li class="page-item" :class="{'disabled' : nowPage === 0 }" @click.prevent="previousPage">
            <a class="page-link" href="#">Previous</a>
          </li>
          <li class="page-item" :class="{'active' : nowPage === index}" v-for="(item, index) in totalPage" :key="index" @click.prevent="skipPage(index)">
            <a class="page-link" href="#">{{index + 1}}</a>
          </li>
          <li class="page-item" :class="{'disabled' : nowPage === totalPage - 1}" @click.prevent="nextPage">
            <a class="page-link" href="#">Next</a>
          </li>
        </ul>
      </nav>
    </div>
  </main>
  <Footer />
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'
import Header from '../../components/Header.vue'
import Footer from '../../components/Footer.vue'

const datastore = ref([])
const temp = ref([])
const productNames = ref([])
const loading = ref(true)
const searchQuery = ref('')

// Pagination
const renderData = ref([])
const pageBaseNum = 8
const nowPage = ref(0)
const totalPage = computed(() => {
  return Math.ceil(temp.value.length / pageBaseNum) || 1
})

const getOrderData = async () => {
    try {
        const res = await axios.get('/CGA101G1/product/showAllOrderAndDetailsByMemNo')
        datastore.value = res.data.map(order => ({ ...order, openOrderDetail: false }))
        await getProductName()

        // Apply product names to details
        datastore.value.forEach(order => {
            order.orderDetail.forEach(detail => {
                detail.productName = ''
                const match = productNames.value.find(p => p.productNo == detail.productNo)
                if (match) {
                    detail.productName = match.productName
                }
            })
        })

        temp.value = [...datastore.value]
        renderingPage()
    } catch (e) {
        console.error(e)
    } finally {
        loading.value = false
    }
}

const getProductName = async () => {
    try {
        const res = await axios.get('/CGA101G1/product/showAllProductsName')
        productNames.value = res.data
    } catch (e) {
        console.error(e)
    }
}

const keysearch = () => {
    const val = searchQuery.value.trim()
    if (val) {
        temp.value = datastore.value.filter(item => {
            const matchOrderNo = item.orderNo.includes(val)
            const matchProductName = item.orderDetail.some(detail => detail.productName.includes(val))
            return matchOrderNo || matchProductName
        })
    } else {
        temp.value = [...datastore.value]
    }
    nowPage.value = 0 // Reset to first page
    renderingPage()
}

const renderingPage = () => {
    renderData.value = []
    for (let n = 0; n < totalPage.value; n++) {
        renderData.value.push(
            temp.value.slice(n * pageBaseNum, n * pageBaseNum + pageBaseNum)
        )
    }
}

const previousPage = () => {
    if (nowPage.value > 0) nowPage.value--
}
const nextPage = () => {
    if (nowPage.value < totalPage.value - 1) nowPage.value++
}
const skipPage = (pageNum) => {
    nowPage.value = pageNum
}

onMounted(() => {
    getOrderData()
})

</script>
