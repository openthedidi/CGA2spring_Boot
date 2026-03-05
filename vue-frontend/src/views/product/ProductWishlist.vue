<template>
  <Header />
  <main id="app">
    <!-- Content -->
    <div class="container py-5">
      <div class="box-container mb-4">
        <h2 class="product-name text-center">我的最愛列表</h2>
      </div>
      
      <!-- Loading State -->
      <div class="d-flex justify-content-center" v-show="loading">
        <div class="spinner-border" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
      </div>
      
      <!-- Empty State -->
      <div class="d-flex justify-content-center">
        <div v-show="jsonData.length === 0 && !loading" style="font-size: 40px; padding-top: 10%">
          <i class="far fa-grin-beam-sweat"></i>
          <span> 目前您的最愛清單內還沒有商品唷 </span>
          <i class="far fa-grin-beam-sweat"></i>
        </div>
      </div>
      
      <!-- Wishlist Table -->
      <div v-show="jsonData.length !== 0" class="table-content table-responsive cart-table-content">
        <table class="table table-bordered align-middle">
          <thead>
            <tr class="text-uppercase text-nowrap">
              <th style="width: 80px;" class="text-dark fw-500">外部</th>
              <th class="text-dark fw-500">遊戲名稱</th>
              <th style="width: 120px;" class="text-dark fw-500">價錢</th>
              <th style="width: 230px;" class="text-dark text-center fw-500">移出我的最愛清單 / 加入購物車</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in jsonData" :key="index">
              <td class="product-thumbnail">
                <router-link class="text-reset" :to="`/product/detail/${item.productNo}`">
                  <img :src="item.imageUrl" class="img-fluid" width="60" alt="">
                </router-link>
              </td>
              <td class="product-name">
                <router-link class="text-reset" :to="`/product/detail/${item.productNo}`">{{item.productName}}</router-link>
              </td>
              <td class="product-price-cart">
                <span class="amount">${{item.productPrice}}</span>
              </td>
              <td class="product-remove text-end text-nowrap d-flex justify-content-center align-items-center gap-2">
                <button @click="removeFromFavorites(item.productNo)" class="btn btn-sm btn-outline-danger text-nowrap px-3">
                  <i class="bi bi-x lh-1"></i>
                </button>
                <button @click="addToCart(item)" class="btn btn-sm btn-outline-dark text-nowrap px-3">
                  <i class="bi bi-cart"></i> 加入購物車
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </main>
  <Footer />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import Header from '../../components/Header.vue'
import Footer from '../../components/Footer.vue'
import swal from 'sweetalert'

const jsonData = ref([])
const loading = ref(true)

const getFavoritesData = async () => {
  loading.value = true
  try {
    const res = await axios.get('/CGA101G1/product/ShowMemFavoritelist')
    jsonData.value = res.data
  } catch (error) {
    console.error(error)
  } finally {
    loading.value = false
  }
}

const removeFromFavorites = async (productNo) => {
  try {
    await axios.get(`/CGA101G1/product/deleteMemFavor?ProductNo=${productNo}`)
    getFavoritesData()
  } catch (error) {
    console.error(error)
  }
}

const addToCart = async (item) => {
  try {
    await axios.post(`/CGA101G1/product/add2ShoppingCart?ProductNo=${item.productNo}&ProductSales=1&ProductTotalPrice=${item.productPrice}&ProductName=${item.productName}`)
    swal("加入成功", `${item.productName} 已加入購物車`, "success")
  } catch (error) {
    console.error(error)
  }
}

onMounted(() => {
  getFavoritesData()
})
</script>

<style scoped>
/* Scoped styles */
</style>
