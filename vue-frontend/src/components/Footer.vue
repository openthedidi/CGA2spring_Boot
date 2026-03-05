<template>
  <footer class="bg-white footer">
      <div class="footer-top py-6">
          <div class="container">
              <div class="row">
                  <section class="footer">
                      <div class="box-container">
                          <div class="box">
                              <h3>會員專區</h3>
                              <router-link to="/mem/login">會員登入</router-link> 
                              <a href="/frontend/mem/register.html">會員註冊</a> 
                              <router-link to="/product/order">訂單管理</router-link> 
                              <a href="#">二手遊戲申請上架單管理</a>
                          </div>

                          <div class="box">
                              <h3>快速連結</h3>
                              <router-link to="/">首頁</router-link> 
                              <router-link to="/product/shop">遊戲商城</router-link> 
                              <a href="/frontend/bid/listallbid.html">二手競標商城</a> 
                              <a href="/frontend/forum/forumHomePage.jsp">遊戲討論區</a>
                          </div>

                          <div class="box">
                              <h3>聯絡我們</h3>
                              <a href="#">02-3456-7890</a> <a href="#">03-1111-2222</a> <a
                                  href="#">CGA101G1@gmail.com</a> <a href="#">anasbhai@gmail.com</a>
                          </div>

                          <div class="box">
                              <h3>關於我們</h3>
                              <a href="#">管理團隊</a> <a href="#">創始理念</a> <a href="#">insuagram</a>
                              <a href="#">linkedin</a>
                          </div>
                      </div>

                      <div class="credit">
                          copyright@2022 by <span>CGA101G1</span>
                      </div>

                  </section>
              </div>
          </div>
      </div>

  </footer>
</template>

<script setup>
</script>

<style scoped>
</style>
