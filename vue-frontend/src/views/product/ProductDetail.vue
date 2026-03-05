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
    
    <!-- Product Details -->
    <section class="product-details py-6">
      <div class="container">
        <div class="row align-items-start">
          <!-- Product Gallery -->
          <div class="col-lg-6 product-gallery">
            <div class="border rounded p-3 text-center position-relative">
              <div v-if="jsonData.Pics && jsonData.Pics.length > 0">
                <a class="gallery-link position-absolute top-0 end-0 p-3" href="#" @click.prevent="isLightboxOpen = true" style="font-size: 1.5rem; color: #555;">
                  <i class="bi bi-arrows-fullscreen"></i>
                </a>
                <img :src="`data:image/png;base64,${jsonData.Pics[0].productPicContentBase64}`" class="img-fluid" style="width:80%; max-height: 400px; object-fit: contain; cursor: zoom-in;" @click="isLightboxOpen = true">
              </div>
              <div v-else>
                <p class="text-muted mt-5 mb-5">沒有商品圖片</p>
              </div>
            </div>
          </div>
          
          <!-- Native Vue Lightbox Modal -->
          <div v-if="isLightboxOpen && jsonData.Pics && jsonData.Pics.length > 0" class="vue-lightbox position-fixed top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center" style="background: rgba(0,0,0,0.8); z-index: 1050;" @click.self="isLightboxOpen = false">
            <button class="btn btn-dark position-absolute top-0 end-0 m-4 fs-3" @click="isLightboxOpen = false">&times;</button>
            <img :src="`data:image/png;base64,${jsonData.Pics[0].productPicContentBase64}`" style="max-width: 90%; max-height: 90vh; object-fit: contain; cursor: zoom-out;" @click="isLightboxOpen = false">
          </div>
          
          <!-- Product Info -->
          <div class="col-lg-6 ps-lg-5 pt-5 pt-lg-0">
            <div class="product-detail">
              <div class="products-brand pb-2">
                <span>Game Name</span>
              </div>
              <div class="products-title mb-2">
                <h1 class="h1">{{jsonData.productName}}</h1>
              </div>
              <div class="col-sm-6">
                <div class="rating-star small">
                  <i class="bi small bi-star-fill active" v-for="index in caledCommentsData.avgCommentStar" :key="'active-' + index"></i>
                  <i class="bi small bi-star" v-for="index in (5 - (caledCommentsData.avgCommentStar || 0))" :key="'inactive-' + index"></i>
                </div>
              </div>
              <div class="product-description" style="font-size: medium;">
                <p>{{jsonData.itemProdDescription}}</p>
              </div>
              
              <div class="product-price fs-3 fw-500 mb-2">
                <span class="text-primary">NT.{{jsonData.productPrice}}</span>
              </div>
              
              <div class="product-detail-actions d-flex flex-wrap pt-3" style="height: 50px;">
                <div class="cart-qty me-3 mb-3 d-flex align-items-center">
                  <button type="button" @click="quantity++" class="inc qty-btn">+</button>
                  <div class="card" style="width: 50px; height: 100%;">
                    <p class="cart-qty-input form-control text-center m-0 border-0 bg-white" style="height: 100%; font-size: 1rem;">{{ quantity }}</p>
                  </div>
                  <button type="button" @click="quantity > 1 ? quantity-- : null" class="dec qty-btn">-</button>
                </div>
                <div class="cart-button mb-3 d-flex">
                  <a @click="addToCart" class="btn btn-dark me-3">
                    <i class="bi bi-cart"></i> 加入購物車
                  </a>
                  <router-link to="/product/cart" @click="addToCart" class="btn btn-dark me-3">
                    <i class="fas fa-hand-holding-usd"></i> 立即購買
                  </router-link>
                  <button @click="addToFavorites" class="btn btn-outline-dark me-3">
                    <i class="bi bi-heart"></i>
                  </button>
                </div>
              </div>
              
              <div class="pt-3 border-top mt-3 small" style="font-size: medium;">
                <p class="theme-link mb-2">
                  <label class="m-0 text-dark">支援平台:</label>
                  <a>{{jsonData.gamePlatformName}}</a>
                </p>
                <p class="theme-link mb-2">
                  <label class="m-0 text-dark">遊戲種類:</label>
                  <a>{{jsonData.gameTypeName}}</a>
                </p>
                <p class="theme-link mb-2">
                  <label class="m-0 text-dark">遊戲公司:</label>
                  <a>{{jsonData.gameCompanyName}}</a>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    <!-- 實機照片 -->
    <section v-if="jsonData.Pics && jsonData.Pics.length > 2" class="container mt-5">
      <h3 class="sub-heading"> 實機照片 </h3>
      <div id="carouselExampleControls" class="carousel slide mx-auto" data-bs-ride="carousel" style="width: 50%;">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img :src="`data:image/png;base64,${jsonData.Pics[1].productPicContentBase64}`" class="d-block w-100">
          </div>
          <div class="carousel-item">
            <img :src="`data:image/png;base64,${jsonData.Pics[2].productPicContentBase64}`" class="d-block w-100">
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </section>
    
    <!-- 評論區 -->
    <section v-show="reviewShow" class="container mt-5">
      <div class="pd-collapse-row">
        <div class="rating-star small mx-auto text-center">
          <h1 class="sub-heading"> <span style="color:black;">會員評鑑 ： </span>
            <i class="bi small bi-star-fill active" v-for="index in caledCommentsData.avgCommentStar" :key="'cal-active-' + index"></i>
            <i class="bi small bi-star" v-for="index in (5 - (caledCommentsData.avgCommentStar || 0))" :key="'cal-inactive-' + index"></i>
            <span v-if="caledCommentsData.countComment > 0">
              {{(caledCommentsData.sumCommentStar / caledCommentsData.countComment).toFixed(2)}}/5
              ({{caledCommentsData.countComment}} 人已評論)
            </span>
          </h1>
        </div>
        
        <div class="collapse show mt-4">
          <div class="pd-collapse-box">
             <!-- Reviews rendered manually according to pagination -->
             <div v-for="(item, index) in renderData[nowPage]" :key="index" class="d-flex review-box border-top mt-3 pt-3">
                <div>
                   <div class="review-image sm">
                      <img class="img-fluid" :src="item.memPicURL" alt="Member Avatar">
                   </div>
                </div>
                <div class="col ps-3">
                   <h5 class="m-0">{{item.memAccount}}</h5>
                   <div class="rating-star small">
                      <i class="bi small bi-star-fill active" v-for="starIndex in Number(item.commentStar)" :key="'star-act-' + index + '-' + starIndex"></i>
                      <i class="bi small bi-star" v-for="starIndex in (5 - Number(item.commentStar))" :key="'star-inact-' + index + '-' + starIndex"></i>
                   </div>
                   <span>購買時間：{{item.commentTime}}</span>
                   <p class="m-0 pt-2 small">評論：{{item.commentCotent}}</p>
                </div>
             </div>
             
             <!-- Pagination -->
             <nav class="d-flex justify-content-center mt-4" v-if="totalPage > 1">
               <ul class="pagination">
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
        </div>
      </div>
    </section>
  </main>
  <Footer />
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'
import Header from '../../components/Header.vue'
import Footer from '../../components/Footer.vue'
import swal from 'sweetalert'

const route = useRoute()

const jsonData = ref({ Pics: [] })
const commentsData = ref([])
const caledCommentsData = ref({ countComment: 0, sumCommentStar: 0, avgCommentStar: 0 })
const reviewShow = ref(false)

const isLightboxOpen = ref(false)

const quantity = ref(1)

const renderData = ref([])
const pageBaseNum = 5
const nowPage = ref(0)
const totalPage = computed(() => Math.ceil(commentsData.value.length / pageBaseNum))

const getProductData = async () => {
    const productId = route.params.id || route.query.ProductNo
    if (!productId) return

    try {
        const res = await axios.get(`/CGA101G1/product/OneProductDetail?ProductNo=${productId}`)
        jsonData.value = res.data
        jsonData.value.Pics = []
        getPicData(productId)
    } catch (e) {
        console.error(e)
    }
}

const getPicData = async (productId) => {
    try {
        const res = await axios.get(`/CGA101G1/product/ProductPicAll?ProductNo=${productId}`)
        jsonData.value.Pics = res.data
    } catch (e) {
        console.error(e)
    }
}

const getCommentsData = async () => {
    const productId = route.params.id || route.query.ProductNo
    if (!productId) return

    try {
        const res = await axios.get(`/CGA101G1/product/showOneProductAllComments?ProductNo=${productId}`)
        commentsData.value = res.data
        if (commentsData.value.length !== 0) {
            reviewShow.value = true
        }
        renderingPage()
    } catch (e) {
        console.error(e)
    }
}

const getCaledCommentsData = async () => {
    const productId = route.params.id || route.query.ProductNo
    if (!productId) return

    try {
        const res = await axios.get(`/CGA101G1/product/showProductCaledComment?ProductNo=${productId}`)
        caledCommentsData.value = res.data
    } catch (e) {
        console.error(e)
    }
}

const addToCart = async () => {
    try {
        const totalPrice = quantity.value * jsonData.value.productPrice
        await axios.post(`/CGA101G1/product/add2ShoppingCart?ProductNo=${jsonData.value.productNo}&ProductSales=${quantity.value}&ProductTotalPrice=${totalPrice}&ProductName=${jsonData.value.productName}`)
        swal("加入成功", `${jsonData.value.productName} 已加入購物車`, "success")
    } catch (e) {
        console.error(e)
    }
}

const addToFavorites = async () => {
    try {
        const res = await axios.post(`/CGA101G1/product/AddProduct2FavFromAlone?ProductNo=${jsonData.value.productNo}`)
        if (res.data == 0) swal("系統訊息", "請先登入會員唷", "warning")
        else if (res.data == 1) swal("系統訊息", "商品已成功添加至我的最愛", "success")
        else if (res.data == 2) swal("系統訊息", "我的最愛清單上已經有這個商品囉", "info")
    } catch (e) {
        console.error(e)
    }
}

const renderingPage = () => {
    renderData.value = []
    for (let n = 0; n < totalPage.value; n++) {
        renderData.value.push(
            commentsData.value.slice(n * pageBaseNum, n * pageBaseNum + pageBaseNum)
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
    getProductData()
    getCommentsData()
    getCaledCommentsData()
})

watch(() => route.params.id, () => {
    getProductData()
    getCommentsData()
    getCaledCommentsData()
})

</script>
