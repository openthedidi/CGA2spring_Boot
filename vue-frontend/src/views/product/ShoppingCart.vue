<template>
  <Header />
  <main>
    <!-- Breadcrumb -->
    <div class="py-3 bg-gray-100">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-6 my-2">
            <h1 class="m-0 h4 text-center text-lg-start">Shopping Cart</h1>
          </div>
          <div class="col-6 my-2">
            <ol class="breadcrumb dark-link m-0 small justify-content-center justify-content-lg-end">
              <li class="breadcrumb-item"><router-link class="text-nowrap" to="/"><i class="bi bi-home"></i>Home</router-link></li>
              <li class="breadcrumb-item text-nowrap active" aria-current="page">Shopping Cart</li>
            </ol>
          </div>
        </div>
      </div>
    </div>
    <!-- End Breadcrumb -->
    
    <!-- Cart Table -->
    <div class="py-6" id="app">
      <div class="container">
        <!-- Cart Table -->
        <div class="table-content table-responsive cart-table-content">
          <H2>購物車明細</H2>
          <table class="table table-bordered align-middle">
            <thead>
              <tr class="text-uppercase" style="font-size: large;">
                <th class="text-dark text-center fw-500 text-nowrap">遊戲名稱</th>
                <th class="text-dark text-center fw-500 text-nowrap">單價</th>
                <th class="text-dark text-center fw-500 text-nowrap">數量</th>
                <th class="text-dark text-center fw-500 text-nowrap">總金額</th>
                <th class="text-dark fw-500 text-center text-nowrap">移除購買項目</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in datastore" :key="index">
                <td class="text-center product-name">
                  <router-link class="text-reset" :to="`/product/detail/${item.productNo}`">{{item.productName}}</router-link>
                </td>
                <td class="text-center product-price-cart">
                  <span class="amount">{{item.productTotalPrice/item.productSales}}</span>
                </td>
                <td class="text-center product-quantity">
                  <div class="cart-qty d-inline-flex">
                    <a class="dec qty-btn" href="#" @click.prevent="modifyCart(item, 'reduce')">-</a>
                    <input class="cart-qty-input form-control" type="text" :value="item.productSales" readonly>
                    <a class="inc qty-btn" href="#" @click.prevent="modifyCart(item, 'add')">+</a>
                  </div>
                </td>
                <td class="text-center product-subtotal">{{item.productTotalPrice}}</td>
                <td class="text-center text-nowrap">
                  <a href="#" @click.prevent="removeFromCart(item)" class="btn btn-sm btn-outline-danger text-nowrap px-3">
                    <i class="bi bi-x lh-1"></i> <span class="d-md-inline-block">刪除</span>
                  </a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        
        <div v-if="(datastore.length === 0)">
          <div style="font-size:large; text-align:center">
             <i class="fas fa-shopping-cart"></i> 目前購物車中還沒有東西唷~ <i class="fas fa-shopping-cart"></i>
          </div>
        </div>
        
        <div class="d-flex mt-4">
          <div>
            <router-link class="btn btn-outline-dark" to="/product/shop">
              <i class="ci-arrow-left mt-sm-0 me-1"></i>
              <span class="d-none d-sm-inline">繼續購物</span>
            </router-link>
          </div>
        </div>
        
        <div v-show="(datastore.length !== 0)">
          <form class="card mt-5" @submit.prevent="submitOrder">
            <div class="card-header py-3">
              <h4 class="m-0">請填寫訂購資料</h4>
            </div>
            <div class="card-body">
              <div class="row g-3">
                <div class="col-sm-6">
                  <label class="form-label" style="font-size: 1.5em;" for="ReceiverName">收件人姓名</label>
                  <input class="form-control" type="text" id="ReceiverName" required name="ReceiverName" @input="nameCheck" v-model="memInfo.memName">
                  <div style="color: red;" :style="{visibility: nameInput ? 'visible' : 'hidden'}">
                    請輸入正確格式的名字，不應包含特殊符號、數字或是只填空格!!
                  </div>
                </div>
                <div class="col-sm-6">
                  <label class="form-label" for="ReceiverPhone" style="font-size: 1.5em;">聯絡手機</label>
                  <input class="form-control" type="number" id="ReceiverPhone" required name="ReceiverPhone" @input="phoneCheck" v-model="memInfo.memMobile">
                  <div style="color: red;" :style="{visibility: phoneInput ? 'visible' : 'hidden'}">
                    請輸入正確的手機格式!!
                  </div>
                </div>
                <!-- Address Area -->
                <div class="col-sm-6">
                  <label class="form-label" for="Rod" style="font-size: 1.5em;">地址</label>
                  <div class="d-flex gap-2 mb-2">
                     <select class="form-select" v-model="memInfo.City" required>
                         <option value="">請選擇縣市</option>
                         <option v-for="c in cityList" :key="c.name" :value="c.name">{{c.name}}</option>
                     </select>
                     <select class="form-select" v-model="memInfo.Dist" required>
                         <option value="">請選擇鄉鎮區</option>
                         <option v-for="d in distList" :key="d.name" :value="d.name">{{d.name}}</option>
                     </select>
                  </div>
                  <input class="form-control" type="text" id="Rod" required name="Rod" v-model="memInfo.Add" @input="RodInputCheck">
                  <div style="color: red;" :style="{visibility: RodInput ? 'visible' : 'hidden'}">
                    請輸入正確格式的地址，不應包含特殊符號及英文或是只填空格!!
                  </div>
                </div>
                
                <div class="col-sm-6">
                  <label class="form-label" for="PickupMethod" style="font-size: 1.5em;">取貨方式</label>
                  <select class="form-select" id="PickupMethod" required name="PickupMethod" @change="feeCheck">
                    <option value="0">宅配</option>
                    <option value="1">超商取貨</option>
                  </select>
                  <label class="form-label mt-2" for="shippingFee" style="font-size: 1.5em;">運費</label>
                  <select class="form-select" id="shippingFee" disabled name="shippingFee" :value="shippingFeeCal">
                    <option value="0">宅配運費:100元</option>
                    <option value="1">超商取貨運費:60元</option>
                  </select>
                </div>
                
                <div class="col-sm-6">
                  <label class="form-label" for="payMethod" style="font-size: 1.5em;">付款方式</label>
                  <select class="form-select" id="payMethod" required name="payMethod">
                    <option value="0">信用卡</option>
                    <option value="1">超商取貨付款</option>
                    <option value="2">匯款</option>
                  </select>
                </div>
                
                <div class="col-md-6 col-lg-8" style="width: 50%;">
                  <label class="form-label" style="font-size: 1.5em;">我的優惠券清單</label>
                  <select class="form-select" id="MemCouponNo" required @change="getCoup">
                    <option value="0">不使用優惠券</option>
                    <option v-for="(item, index) in coupons" :key="index" :value="`${item.discountPrice},${item.couponTypeNo}`">
                      優惠券編號：{{item.couponTypeNo}}：優惠金額-{{item.discountPrice}}
                    </option>
                  </select>
                </div>
                
                <div class="col-sm-6" style="width: 49%;">
                  <div class="card">
                    <div class="card-header bg-transparent py-3">
                      <h6 class="m-0 mb-1" style="font-size: 1.8em;">本次訂單</h6>
                    </div>
                    <!-- 結帳明細 -->
                    <div class="card-body ">
                      <ul class="list-unstyled">
                        <li class="d-flex justify-content-between align-items-center mb-2">
                          <h6 class="me-2 text-body">原價</h6><span class="text-end">{{totalPrice}}</span>
                        </li>
                        <li class="d-flex justify-content-between align-items-center mb-2">
                          <h6 class="me-2 text-body">優惠券折抵金額</h6><span class="text-end">-{{selectCoupon}}</span>
                        </li>
                        <li class="d-flex justify-content-between align-items-center mb-2">
                          <h6 class="me-2 text-body">運費</h6><span class="text-end">+{{shipFeeConfirm}}</span>
                        </li>
                        <li class="d-flex justify-content-between align-items-center border-top pt-3 mt-3">
                          <h6 class="me-2" style="font-size: 1.8em;">最後所需支付總價： <span>{{ finalPrice }}</span></h6>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
                
                <div class="col-12">
                  <button type="submit" class="btn btn-primary" v-show="ckeckoutOK">送出訂單</button>
                </div>
              </div>
            </div>
          </form>
        </div>
        
        <div v-if="loading" style="position:fixed;left: 40%; top: 40%;background-color: rgb(87, 98, 107); width: 20%; z-index: 1000;" class="p-3 border rounded text-center">
            <h3 style="color: white;">成立訂單中....</h3>
            <div class="spinner-border text-info mt-3" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>
      </div>
    </div>
  </main>
  <Footer />
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import axios from 'axios'
import Header from '../../components/Header.vue'
import Footer from '../../components/Footer.vue'
import swal from 'sweetalert'

const datastore = ref([])
const totalPrice = ref(0)
const coupons = ref([])
const memInfo = ref({ memName: '', memMobile: '', City: '', Dist: '', Add: '' })
const postData = ref([])
const selectCoupon = ref(0)
const selectCouponNo = ref('')
const memCouponNo = ref(0)
const postNo = ref(0)

const form = ref(false)
const loading = ref(false)

const nameInput = ref(false)
const RodInput = ref(false)
const phoneInput = ref(false)
const shippingFeeCal = ref(0)
const shipFeeConfirm = ref(100)
const ckeckoutOK = ref(false)

// Optional chaining for safe compute
const finalPrice = computed(() => {
    return Math.max(0, totalPrice.value - selectCoupon.value + shipFeeConfirm.value)
})

const getProductData = async () => {
    try {
        const res = await axios.get('/CGA101G1/product/showCart')
        datastore.value = res.data
        totalPrice.value = 0
        datastore.value.forEach(element => {
            totalPrice.value += element.productTotalPrice
        })
        validateForm()
    } catch (e) {
        console.error(e)
    }
}

const getPostData = async () => {
    try {
        const res = await axios.get('https://demeter.5fpro.com/tw/zipcodes.json')
        postData.value = res.data
    } catch (e) {
        console.error(e)
    }
}

const getMemData = async () => {
    try {
        const res = await axios.get('/CGA101G1/product/showShoppingMemInfo')
        if (res.data) {
           memInfo.value = res.data
           validateForm()
        }
    } catch (e) {
        console.error(e)
    }
}

const getMemCoupons = async () => {
    try {
        const res = await axios.get('/CGA101G1/order/showMemAllC')
        coupons.value = res.data
    } catch (e) {
        console.error(e)
    }
}

// Data aggregation for Zipcodes (Vue 3 simpler alternative to jQuery TwZipcode)
const cityList = computed(() => {
    const list = []
    const seen = new Set()
    postData.value.forEach(item => {
        if (!seen.has(item.city_name)) {
            seen.add(item.city_name)
            list.push({ name: item.city_name })
        }
    })
    return list
})

const distList = computed(() => {
    if (!memInfo.value.City) return []
    return postData.value.filter(item => item.city_name === memInfo.value.City).map(item => ({
        name: item.name,
        zipcode: item.zipcode
    }))
})

watch(() => memInfo.value.Dist, (newDist) => {
    const target = distList.value.find(d => d.name === newDist)
    if (target) postNo.value = target.zipcode
})

// Modifications
const modifyCart = async (item, action) => {
    try {
        const singlePrice = item.productTotalPrice / item.productSales
        const url = action === 'add' ? '/CGA101G1/product/shoppingCartModAdd' : '/CGA101G1/product/shoppingCartReduce'
        await axios.get(`${url}?ProductNo=${item.productNo}&ProductSales=1&ProductTotalPrice=${singlePrice}&ProductName=${item.productName}`)
        getProductData()
    } catch (e) {
        console.error(e)
    }
}

const removeFromCart = async (item) => {
    try {
        const singlePrice = item.productTotalPrice / item.productSales
        await axios.get(`/CGA101G1/product/shoppingCartRemoveAll?ProductNo=${item.productNo}&ProductSales=1&ProductTotalPrice=${singlePrice}&ProductName=${item.productName}`)
        getProductData()
    } catch(e) {
        console.error(e)
    }
}

const getCoup = (e) => {
    const val = e.target.value
    if (val === "0") {
        selectCoupon.value = 0
        memCouponNo.value = 0
        selectCouponNo.value = ''
        return
    }
    const parts = val.split(',')
    selectCoupon.value = Number(parts[0])
    selectCouponNo.value = parts[1]
    
    const target = coupons.value.find(c => c.couponTypeNo == selectCouponNo.value)
    if (target) {
        memCouponNo.value = target.memCouponNo
    }
}

const nameCheck = () => {
    const rules = /[+\-/^~{}:@$!%*?&0-9]/
    const val = memInfo.value.memName || ''
    nameInput.value = rules.test(val) || val.trim().length === 0
    validateForm()
}

const phoneCheck = () => {
    const rules = /^09\d{8}$/
    const val = memInfo.value.memMobile || ''
    phoneInput.value = !rules.test(val) || val.trim().length === 0
    validateForm()
}

const RodInputCheck = () => {
    const val = memInfo.value.Add || ''
    const rules = /[+\-/^~{}:@$!%*?&a-zA-Z]/
    RodInput.value = rules.test(val) || val.trim().length === 0
    validateForm()
}

const feeCheck = (e) => {
    if (e.target.value == "1") {
        shippingFeeCal.value = 1
        shipFeeConfirm.value = 60
    } else {
        shippingFeeCal.value = 0
        shipFeeConfirm.value = 100
    }
}

const validateForm = () => {
    ckeckoutOK.value = false
    if (datastore.value.length === 0) return
    if (!memInfo.value.memName || nameInput.value) return
    if (!memInfo.value.memMobile || phoneInput.value) return
    if (!memInfo.value.City || !memInfo.value.Dist) return
    if (!memInfo.value.Add || RodInput.value) return
    
    ckeckoutOK.value = true
}

const submitOrder = async () => {
    if (!ckeckoutOK.value) return
    loading.value = true
    try {
        // Because of avoiding full page reaload on forms in modern SPA, 
        // normally we would adapt this to an Axios request. 
        // Here we do a mock conversion to axios submitting the order formData.
        const formData = new URLSearchParams()
        formData.append('ReceiverName', memInfo.value.memName)
        formData.append('ReceiverPhone', memInfo.value.memMobile)
        formData.append('city', memInfo.value.City)
        formData.append('dist', memInfo.value.Dist)
        formData.append('Rod', memInfo.value.Add)
        formData.append('PickupMethod', document.getElementById('PickupMethod').value)
        formData.append('payMethod', document.getElementById('payMethod').value)
        formData.append('MemCouponNo', memCouponNo.value)
        formData.append('couponTypeNo', selectCouponNo.value)
        formData.append('OrderTotalPrice', finalPrice.value)
        
        // await axios.post('/CGA101G1/order/addtoOrderAndOrderlist', formData)
        
        // Temporary window.location substitution pattern since the original used a native form action
        const formObj = document.createElement('form')
        formObj.method = "POST"
        formObj.action = "/CGA101G1/order/addtoOrderAndOrderlist"
        
        for (const [key, value] of formData.entries()) {
            const input = document.createElement('input')
            input.type = 'hidden'
            input.name = key
            input.value = value
            formObj.appendChild(input)
        }
        document.body.appendChild(formObj)
        formObj.submit()
        
    } catch (e) {
        console.error(e)
        loading.value = false
        swal("錯誤", "建立訂單失敗", "error")
    }
}

onMounted(() => {
    getPostData()
    getProductData()
    getMemCoupons()
    getMemData()
})

</script>
