<template>
  <Header />
  <main id="app">
    <!-- Breadcrumb -->
    <div class="py-3 bg-gray-100">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-6 my-2">
            <h1 class="m-0 h4 text-center text-lg-start">Post Comment</h1>
          </div>
          <div class="col-6 my-2">
            <ol class="breadcrumb dark-link m-0 small justify-content-center justify-content-lg-end">
              <li class="breadcrumb-item"><router-link class="text-nowrap" to="/"><i class="bi bi-home"></i>Home</router-link></li>
              <li class="breadcrumb-item text-nowrap active" aria-current="page">Post Comment</li>
            </ol>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Comment Form -->
    <div class="py-6">
      <div class="container" style="width: 50%;">
        <form class="card mt-5" @submit.prevent="submitComment">
          <div class="card-header py-3" style="text-align:center;">
            <h2 class="m-0">發表評論</h2>
            <h3>遊戲名稱：{{ datastore.productName || '載入中...' }}</h3>
          </div>
          <div class="card-body">
            <div class="row g-3" style="padding-left: 20%; padding-right: 20%;">
              <div class="col-12">
                <label class="form-label" for="orderNo" style="font-size: 1.5em;">訂單編號</label>
                <input class="form-control bg-light" type="text" id="orderNo" name="orderNo" v-model="orderNo" readonly>
              </div>
              
              <!-- Hidden productNo input as required by the backend in the original design if it was a standard form -->
              <input type="hidden" name="productNo" :value="datastore.productNo">

              <div class="col-12">
                <label class="form-label" for="commentCotent" style="font-size: 1.5em;">發表評論文章</label>
                <textarea class="form-control" id="commentCotent" name="commentCotent" v-model="commentContent" @input="postCheck" required style="font-size: 1.2em; min-height: 120px;"></textarea>
                <div class="text-danger mt-1" :style="{ visibility: postInputError ? 'visible' : 'hidden' }">
                  文章中不能包含特殊符號 + - / ^ { } # : @ $ % * & 或是只填空格唷
                </div>
              </div>

              <div class="col-12">
                <label class="form-label" for="CommentStar" style="font-size: 1.5em;">我要給星星</label>
                <input type="range" class="form-range" min="0" max="5" step="1" id="CommentStar" name="CommentStar" v-model="commentStar" required>
                <table style="width: 100%" class="text-muted mt-2">
                  <tr>
                    <th class="text-start">0</th>
                    <th class="text-center">1</th>
                    <th class="text-center">2</th>
                    <th class="text-center">3</th>
                    <th class="text-center">4</th>
                    <th class="text-end">5</th>
                  </tr>
                </table>
              </div>

              <div class="col-12 text-center mt-4">
                <button type="submit" class="btn btn-primary px-5 py-2" :disabled="postInputError || !commentContent">送出評論</button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </main>
  <Footer />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'
import Header from '../../components/Header.vue'
import Footer from '../../components/Footer.vue'
import swal from 'sweetalert'

const route = useRoute()
const router = useRouter()

const datastore = ref({})
const orderNo = ref('')
const commentContent = ref('')
const commentStar = ref(5)
const postInputError = ref(true) // Initial true to disable submit until input

const getProductData = async () => {
  const productNo = route.query.ProductNo
  orderNo.value = route.query.OrderNo

  if (!productNo || !orderNo.value) {
    swal("錯誤", "缺少必要的參數", "error").then(() => {
        router.back()
    })
    return
  }

  try {
    const res = await axios.get(`/product/OneProductDetail?ProductNo=${productNo}`)
    datastore.value = res.data
  } catch (error) {
    console.error("取ProductData失敗：", error)
  }
}

const postCheck = () => {
  const content = commentContent.value
  const rules = new RegExp('[+\\-/^{}#:@$%*&]') // Escape the hyphen
  
  if (rules.test(content) || content.trim().length === 0) {
    postInputError.value = true
  } else {
    postInputError.value = false
  }
}

const submitComment = async () => {
    if (postInputError.value || !commentContent.value) return

    try {
        // Mocking the form submission for the Vue 3 architecture
        const formData = new URLSearchParams()
        formData.append('orderNo', orderNo.value)
        formData.append('productNo', datastore.value.productNo)
        formData.append('commentCotent', commentContent.value)
        formData.append('CommentStar', commentStar.value)

        await axios.post('/orderDetail/addCommit', formData)
        
        swal("成功", "評論已送出", "success").then(() => {
            router.push('/product/order')
        })

    } catch (error) {
       console.error(error)
       // Original didn't have error handling, adding a basic one
       swal("錯誤", "送出評論失敗", "error")
    }
}

onMounted(() => {
  getProductData()
})

</script>
