<template>
  <div class="agree-container">
    <div class="back-btn" @click="goBack">
      <i class="fas fa-times" aria-hidden="true"></i>
    </div>
    
    <div class="title">
      POP.Game會員注意事項
    </div>
    
    <div class="content">
      <p>購物一定有風險，買賣投資有賺有賠，申購前應詳閱公開說明書。</p>
    </div>
    
    <div class="image-wrapper">
      <img src="https://dummyimage.com/600x400/000/fff&text=Alien+Animation" alt="Alien Animation" />
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goBack = () => {
    // Try using Vue Router first, fallback to native history if direct link
    if (window.history.length > 1) {
        router.back()
    } else {
        router.push('/')
    }
}
</script>

<style scoped>
.agree-container {
    min-height: 100vh;
    background-color: lightblue;
    position: relative;
    overflow-x: hidden;
}

.title {
    font-size: 50px;
    justify-content: center;
    display: flex;
    width: 100%;
    color: sandybrown;
    padding-top: 2rem;
    text-align: center;
}

.content {
    font-size: 20px;
    justify-content: center;
    display: flex;
    width: 100%;
    margin-top: 1rem;
    padding: 0 15px;
    text-align: center;
}

.back-btn {
    position: fixed;
    top: 20px;
    left: 20px;
    font-size: 40px;
    cursor: pointer;
    color: #333;
    z-index: 10;
    transition: color 0.3s;
}

.back-btn:hover {
    color: #000;
}

.image-wrapper {
    width: 100%;
    display: flex;
    justify-content: center;
    margin-top: 2rem;
}

img {
    max-width: 100%;
    height: auto;
}
</style>
