<template>
  <header>
    <div class="logo-div">
        <img src="/images/logo.png" alt="Logo">
        <router-link to="/" class="logo">帕Game</router-link>
    </div>

    <nav class="navbar" :class="{ active: isMenuActive }">
        <!-- 上方標籤列 -->
        <router-link class="active" to="/">首頁</router-link>
        <router-link to="/product/shop" class="nav-link">商品區</router-link>
        <a href="/CGA101G1/frontend/bid/listallbid.html" class="nav-link">競標區</a>
        <a href="/CGA101G1/frontend/forum/forumHomePage.jsp" class="nav-link">討論區</a>
    </nav>

    <div class="icons">
        <!--右上方小icon區-->
        <i class="fas" id="menu-bars" :class="[isMenuActive ? 'fa-times' : 'fa-bars']" @click="toggleMenu"></i>
        <router-link to="/mem/login" class="fas fa-solid fa-user-astronaut" id="login-icon"></router-link>
        <router-link to="/product/wishlist" class="fas fa-heart"></router-link>
        <router-link to="/product/cart" class="fas fa-shopping-cart" id="cart"></router-link>
    </div>
  </header>
</template>

<script setup>
import { ref } from 'vue'

const isMenuActive = ref(false)

const toggleMenu = () => {
    isMenuActive.value = !isMenuActive.value
}
</script>

<style scoped>
/* Scoped styles will go here if needed, but assuming global inclusion based on the previous structure */
</style>
