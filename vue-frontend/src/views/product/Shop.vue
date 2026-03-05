<template>
  <Header />
  <main id="app">
    <!-- Breadcrumb -->
    <div class="py-3 bg-gray-100">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-6 my-2">
            <h1 class="m-0 h4 text-center text-lg-start">Shop</h1>
          </div>
          <div class="col-lg-6 my-2">
            <ol class="breadcrumb dark-link m-0 small justify-content-center justify-content-lg-end">
              <li class="breadcrumb-item"><router-link class="text-nowrap" to="/"><i class="bi bi-home"></i>Home</router-link></li>
              <li class="breadcrumb-item text-nowrap active" aria-current="page">Product</li>
            </ol>
          </div>
        </div>
      </div>
    </div>
    
    <section class="py-6">
      <div class="container">
        <div class="row">
          <!-- Sidebar -->
          <div class="col-lg-4 col-xl-3 pe-xl-5 pt-9 pt-lg-0 shop-mob-toggle collapse d-lg-block" id="shop_filter">
            <!-- Mobile Toggle -->
            <button class="p-3 btn-close position-absolute top-0 start-0 ms-2 mt-2 d-lg-none" type="button"
                data-bs-toggle="collapse" data-bs-target="#shop_filter" aria-controls="shop_filter"
                aria-expanded="false" aria-label="Toggle navigation">
            </button>
            <div class="shop-sidebar-block">
                <input class="form-control" type="text" name="keysearch" v-model="keyWord" placeholder="搜尋遊戲名稱">
                <div v-show="keyWord">
                    <button class="btn btn-outline-dark me-3 mt-2" @click="searchByKeyword">
                        送出遊戲查詢
                    </button>
                </div>
            </div>
            
            <!-- 遊戲平台 -->
            <div class="shop-sidebar-block mt-4">
                <div class="shop-sidebar-title">
                    <a class="h5" role="button" @click="gamePlatformFliter = !gamePlatformFliter">遊戲平台<i class="bi bi-chevron-up"></i></a>
                </div>
                <div v-show="gamePlatformFliter" class="shop-sidebar-list">
                    <ul>
                        <li class="custom-radio" v-for="(item, index) in gamePlatformTypeData" :key="index" style="height: 40px;">
                            <a href="#" @click.prevent="filterByPlatform(item.gamePlatformNo)" style="width: 100%;">{{item.gamePlatformName}}</a>
                        </li>
                    </ul>
                </div>
            </div>
            
            <!-- 遊戲種類 -->
            <div class="shop-sidebar-block mt-4">
                <div class="shop-sidebar-title">
                    <a class="h5" role="button" @click="gametypeFliter = !gametypeFliter">遊戲種類 <i class="bi bi-chevron-up"></i></a>
                </div>
                <div v-show="gametypeFliter" class="shop-sidebar-list">
                    <ul>
                        <li class="custom-radio" v-for="(item, index) in gameTypeData" :key="index" style="height: 40px;">
                            <a href="#" @click.prevent="filterByType(item.gameTypeNo)" style="width: 100%;">{{item.gameTypeName}}</a>
                        </li>
                    </ul>
                </div>
            </div>
            
            <!-- 價格篩選 -->
            <div class="shop-sidebar-block mt-4">
                <div class="shop-sidebar-title">
                    <a class="h5" @click="moneyFliter = !moneyFliter" role="button">價格 <i class="bi bi-chevron-up"></i></a>
                </div>
                <div v-show="moneyFliter" class="shop-sidebar-list">
                    <ul>
                        <li class="custom-checkbox"><a href="#" @click.prevent="filterByPrice(0, 999)">NT：0 - NT：999</a></li>
                        <li class="custom-checkbox"><a href="#" @click.prevent="filterByPrice(1000, 1499)">NT：1000 - NT：1499</a></li>
                        <li class="custom-checkbox"><a href="#" @click.prevent="filterByPrice(1500, 1999)">NT：1500 - NT：1999</a></li>
                        <li class="custom-checkbox"><a href="#" @click.prevent="filterByPrice(2000, 99999)">NT：2000 - </a></li>
                    </ul>
                    <div class="d-flex align-items-center pt-3">
                        <input type="number" class="form-control form-control-sm" placeholder="NT0" v-model="manaulPriceL">
                        <div class="text-gray-350 mx-2">‒</div>
                        <input type="number" class="form-control form-control-sm" placeholder="NT2000" v-model="manaulPriceH">
                    </div>
                    <button class="btn btn-dark mt-2" @click="filterByPrice(manaulPriceL, manaulPriceH)">送出金額查詢</button>
                </div>
            </div>
          </div>
          
          <!-- Product Box -->
          <div class="col-lg-8 col-xl-9">
             <div class="row g-3">
                <div class="d-flex justify-content-center" v-show="loading">
                    <div class="spinner-border" role="status">
                        <span class="visually-hidden">Loading...</span>
                    </div>
                </div>
                <div class="d-flex justify-content-center" v-show="pages == 0 && !loading" style="font-size:50px;">
                    <i class="far fa-grin-beam-sweat"></i>
                    <span> 查無資訊 </span><i class="far fa-grin-beam-sweat"></i>
                </div>
                
                <div class="col-sm-6 col-lg-4" v-for="(item, index) in displayData" :key="index" style="height:60em">
                    <div class="product-card-1" style="height:90%">
                        <div class="product-card-image">
                            <div class="badge-ribbon">
                                <span class="badge bg-danger">{{item.gamePlatformTypeName}}</span>
                            </div>
                            <div class="product-action">
                                <button @click="addToFavorites(item.productNo)" class="btn btn-outline-primary">
                                    <i class="bi bi-heart"></i>
                                </button>
                            </div>
                            <div class="product-media">
                                <router-link :to="`/product/detail/${item.productNo}`">
                                    <img class="img-fluid" :src="item.imgURL" style="height: 80%;">
                                </router-link>
                                <div class="product-cart-btn">
                                    <button @click="addToCart(item)" class="btn btn-primary btn-sm w-100">
                                        <i class="bi bi-cart"></i> 加入購物車
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="product-card-info">
                            <h6 class="product-title">
                                <router-link :to="`/product/detail/${item.productNo}`">{{item.productName}}</router-link>
                            </h6>
                            <div class="product-price">
                                <span class="text-primary">$ {{item.productPrice}}</span>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Pagination -->
                <div class="ms-auto" style="padding-left: 40%;" v-show="pages > 0">
                    <ul class="pagination">
                        <li class="page-item" :class="{'disabled' : page === 0 }">
                            <a class="page-link" href="#" @click.prevent="previousPage">Previous</a>
                        </li>
                        <li class="page-item" v-for="(item, index) in Math.ceil(pages/9)" :key="index" @click.prevent="skipPage(index)">
                            <a class="page-link" href="#">{{index + 1}}</a>
                        </li>
                        <li class="page-item" :class="{'disabled' : page >= Math.ceil(pages/9) - 1}">
                            <a class="page-link" href="#" @click.prevent="nextPage">Next</a>
                        </li>
                    </ul>
                </div>
             </div>
          </div>
        </div>
      </div>
    </section>
  </main>
  <Footer />
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import axios from 'axios'
import Header from '../../components/Header.vue'
import Footer from '../../components/Footer.vue'
import swal from 'sweetalert'

const router = useRouter()
const route = useRoute()

const loading = ref(true)
const jsonData = ref([])
const temp = ref([])
const pages = ref(0)
const page = ref(0)
const keyWord = ref('')
const gamePlatformTypeData = ref([])
const gameTypeData = ref([])
const manaulPriceL = ref(0)
const manaulPriceH = ref(99999)

const gametypeFliter = ref(false)
const gamePlatformFliter = ref(false)
const moneyFliter = ref(false)

const displayData = computed(() => temp.value.length === 0 ? jsonData.value : temp.value)

// Assuming API endpoints start with '/CGA101G1/product/'
const fetchProductData = async () => {
    loading.value = true
    try {
        const { gameTypeNo, gamePlatformNo, lowPrice, highPrice, keyword } = route.query
        
        if (!gameTypeNo && !gamePlatformNo && !lowPrice && !keyword) {
            const pagesRes = await axios.get('/CGA101G1/product/showSelledCount')
            pages.value = pagesRes.data
            const productRes = await axios.get(`/CGA101G1/product/showPageProduct?Page=${page.value}`)
            jsonData.value = productRes.data
        } else if (gamePlatformNo) {
            const pagesRes = await axios.get(`/CGA101G1/product/showSellAndPlatFormTypeProductPages?gamePlatformNo=${gamePlatformNo}`)
            pages.value = pagesRes.data
            const productRes = await axios.get(`/CGA101G1/product/showSellAndPlatformProduct?gamePlatformNo=${gamePlatformNo}&Page=${page.value}`)
            temp.value = productRes.data
        } else if (gameTypeNo) {
             const pagesRes = await axios.get(`/CGA101G1/product/showSellAndGameTypeProductPages?gameTypeNo=${gameTypeNo}`)
             pages.value = pagesRes.data
             const productRes = await axios.get(`/CGA101G1/product/showSellAndGameTypeProduct?gameTypeNo=${gameTypeNo}&Page=${page.value}`)
             temp.value = productRes.data
        } else if (lowPrice) {
             const pagesRes = await axios.get(`/CGA101G1/product/showSellCountByMoney?lowPrice=${lowPrice}&highPrice=${highPrice}`)
             pages.value = pagesRes.data
             const productRes = await axios.get(`/CGA101G1/product/showInSellByMapAndMoney?lowPrice=${lowPrice}&highPrice=${highPrice}&Page=${page.value}`)
             temp.value = productRes.data
        } else if (keyword) {
             const pagesRes = await axios.get(`/CGA101G1/product/showSellProductByKeyWordPages?keyWord=${keyword}`)
             pages.value = pagesRes.data
             const productRes = await axios.get(`/CGA101G1/product/showSellProductByKeyWord?keyWord=${keyword}&Page=${page.value}`)
             temp.value = productRes.data
        }
    } catch (e) {
        console.error(e)
    } finally {
        loading.value = false
    }
}

const getPlatformAndTypeData = async () => {
    try {
        const platRes = await axios.get('/CGA101G1/gameplatformtype/getAllGamePlatformType')
        gamePlatformTypeData.value = platRes.data
        const typeRes = await axios.get('/CGA101G1/gametype/getAllGameType')
        gameTypeData.value = typeRes.data
    } catch (e) {
        console.error(e)
    }
}

// Navigation methods
const filterByPlatform = (no) => {
    router.push({ query: { gamePlatformNo: no } })
}
const filterByType = (no) => {
    router.push({ query: { gameTypeNo: no } })
}
const filterByPrice = (low, high) => {
    router.push({ query: { lowPrice: low, highPrice: high } })
}
const searchByKeyword = () => {
    if(keyWord.value) {
       router.push({ query: { keyword: keyWord.value } })
    }
}

// Pagination
const skipPage = (index) => {
    page.value = index
    jsonData.value = []
    temp.value = []
    fetchProductData()
}
const nextPage = () => {
    page.value++
    jsonData.value = []
    temp.value = []
    fetchProductData()
}
const previousPage = () => {
    if (page.value > 0) {
        page.value--
        jsonData.value = []
        temp.value = []
        fetchProductData()
    }
}

const addToCart = async (item) => {
    try {
        await axios.post(`/CGA101G1/product/add2ShoppingCart?ProductNo=${item.productNo}&ProductSales=1&ProductTotalPrice=${item.productPrice}&ProductName=${item.productName}`)
        swal("加入成功", `${item.productName} 已加入購物車`, "success")
    } catch (e) {
        console.error(e)
    }
}

const addToFavorites = async (productNo) => {
    try {
        const res = await axios.post(`/CGA101G1/product/AddProduct2Fav?ProductNo=${productNo}`)
        if (res.data == 0) swal("系統訊息", "請先登入會員", "warning")
        else if (res.data == 1) swal("系統訊息", "成功加入我的最愛", "success")
        else if (res.data == 2) swal("系統訊息", "已經在清單中", "info")
    } catch (e) {
        console.error(e)
    }
}

onMounted(() => {
    getPlatformAndTypeData()
    fetchProductData()
})

// Watch route query changes
import { watch } from 'vue'
watch(() => route.query, () => {
    page.value = 0
    temp.value = []
    jsonData.value = []
    fetchProductData()
})

</script>
