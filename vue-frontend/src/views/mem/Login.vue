<template>
  <Header />
  <main>
    <div class="py-3 bg-gray-100">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-6 my-2">
            <h1 class="m-0 h4 text-center text-lg-start">Login</h1>
          </div>
          <div class="col-lg-6 my-2">
            <ol class="breadcrumb dark-link m-0 small justify-content-center justify-content-lg-end">
              <li class="breadcrumb-item"><router-link class="text-nowrap" to="/"><i class="bi bi-home"></i>Home</router-link></li>
              <li class="breadcrumb-item text-nowrap active" aria-current="page">Member</li>
            </ol>
          </div>
        </div>
      </div>
    </div>
    <div class="section" id="app">
      <div class="container">
        <div class="justify-content-center row">
          <div class="col-lg-5 col-xxl-4">
            <div class="card" style="border: 1px solid #aeb4be;">
              <div class="card-header bg-transparent py-3">
                <h3 class="h4 mb-0" style="text-align: center; color: cadetblue;">登入會員</h3>
              </div>
              <label class="form-label" style="padding-left:1em;"> 選擇登入方式:<span class="text-danger"></span></label>
              <div style="margin: 0 0 0 1em; display: flex; gap: 10px;">
                <label><input type="radio" value="username" v-model="loginType"> 帳號</label>
                <label><input type="radio" value="email" v-model="loginType"> 信箱</label>
              </div>

              <template v-if="loginType === 'username'">
                <div class="card-body">
                  <div class="form-group mb-3">
                    <label for="memAccount" class="form-label">帳號:<span class="text-danger">*</span></label>
                    <input style="text-transform: none;" v-model="memAccount" type="text" id="memAccount" class="form-control" placeholder="輸入帳號" key="username">
                  </div>
                  <div class="form-group mb-3">
                    <div class="row align-items-center">
                      <label class="form-label col" for="memPassword">密碼:<span class="text-danger">*</span></label>
                    </div>
                    <input type="password" class="form-control" id="memPassword" placeholder="輸入密碼" v-model="memPassword1" style="text-transform: none;" key="upassword">
                    <span class="col ms-auto small text-end"><a href="/CGA101G1/frontend/mem/EmailForChangePassword.jsp">忘記密碼?</a></span>
                  </div>
                  <div class="form-check mb-3">
                    <input class="form-check-input" type="checkbox" id="remember1" v-model="isChecked1" />
                    <label class="form-check-label" for="remember1"> 記住帳號 </label>
                  </div>
                  <div class="form-group text-center">
                    <button type="button" class="btn btn-primary w-100" @click="checkAccount">登入</button>
                  </div>
                  <div class="error mt-3 text-center" style="color: red; font-size: 15px;" v-if="isShow">{{ errMsg1 }}</div>
                </div>
              </template>

              <template v-if="loginType === 'email'">
                <div class="card-body">
                  <div class="form-group mb-3">
                    <label for="memEmail" class="form-label">信箱:<span class="text-danger">*</span></label>
                    <input style="text-transform: none;" type="text" id="memEmail" class="form-control" placeholder="輸入信箱" key="email" v-model="email">
                  </div>
                  <div class="form-group mb-3">
                    <div class="row align-items-center">
                      <label class="form-label col" for="memPassword2">密碼:<span class="text-danger">*</span></label>
                    </div>
                    <input type="password" class="form-control" id="memPassword2" placeholder="輸入密碼" style="text-transform: none;" key="epassword" v-model="memPassword2">
                    <span class="col ms-auto small text-end"><a href="/CGA101G1/frontend/mem/EmailForChangePassword.jsp">忘記密碼?</a></span>
                  </div>
                  <div class="form-check mb-3">
                    <input class="form-check-input" type="checkbox" id="remember2" v-model="isChecked2" />
                    <label class="form-check-label" for="remember2"> 記住信箱 </label>
                  </div>
                  <div class="form-group text-center">
                    <button type="button" class="btn btn-primary w-100" @click="checkEmail">登入</button>
                  </div>
                  <div class="error mt-3 text-center" style="color: red; font-size: 15px;" v-if="isShow">{{ errMsg2 }}</div>
                </div>
              </template>

              <div class="pt-4 pb-4 text-center">
                <span class="text-muted"><a href="/CGA101G1/frontend/mem/register.html">還沒有加入會員嗎? 點選這裡前往註冊!!</a></span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
  <Footer />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import Header from '../../components/Header.vue'
import Footer from '../../components/Footer.vue'
import swal from 'sweetalert'

const router = useRouter()

const memAccount = ref('')
const memPassword1 = ref('')
const memPassword2 = ref('')
const email = ref('')
const loginType = ref('username')
const isShow = ref(false)
const errMsg1 = ref('')
const errMsg2 = ref('')
const isChecked1 = ref(false)
const isChecked2 = ref(false)

const checkAccount = async () => {
  if (isChecked1.value) {
    localStorage.setItem('memAccount', memAccount.value)
  } else {
    localStorage.removeItem('memAccount')
  }

  try {
    const response = await axios.post('/mem/jwt/login', {
      memAccount: memAccount.value,
      memPassword: memPassword1.value
    })
    
    if (response.data != null && typeof response.data === 'string' && response.data.trim() !== '') {
        document.cookie = response.data 
        swal("歡迎光臨買鳳(ﾉ>ω<)ﾉ", "", "success")
        router.push('/')
    } else {
        isShow.value = true
        errMsg1.value = response.data?.message || '登入失敗'
    }
  } catch (error) {
    swal("Oops...", "帳號或是密碼錯誤囉...", "error")
  }
}

const checkEmail = async () => {
  if (isChecked2.value) {
    localStorage.setItem('memEmail', email.value)
  } else {
    localStorage.removeItem('memEmail')
  }

  try {
    const response = await axios.post('/CGA101G1/mem/EamilLoginServlet', {
      memEmail: email.value,
      memPassword: memPassword2.value
    })

    const { successful, message, initlocation } = response.data
    if (successful) {
      let { memNo, memName, memStatus, memVrfed } = response.data
      sessionStorage.setItem('memNo', memNo)
      sessionStorage.setItem('memName', memName)
      sessionStorage.setItem('memStatus', memStatus)
      sessionStorage.setItem('memVrfed', memVrfed)
      
      swal("歡迎光臨買鳳(ﾉ>ω<)ﾉ", "", "success").then(() => {
        if (initlocation) {
          window.location.href = initlocation
        } else {
          router.push('/')
        }
      })
    } else {
      isShow.value = true
      errMsg2.value = message
    }
  } catch (error) {
    swal("Oops...", "信箱或是密碼錯誤囉...", "error")
  }
}

onMounted(() => {
  const savedEmail = localStorage.getItem('memEmail')
  if (savedEmail) {
    email.value = savedEmail
    isChecked2.value = true
  }
  
  const savedAccount = localStorage.getItem('memAccount')
  if (savedAccount) {
    memAccount.value = savedAccount
    isChecked1.value = true
  }
})
</script>

<style scoped>
/* Scoped styles will go here if needed */
</style>
