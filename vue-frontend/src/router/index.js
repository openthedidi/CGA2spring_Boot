import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/product/shop'
  },
  {
    path: '/product/shop',
    name: 'Shop',
    component: () => import('../views/product/Shop.vue')
  },
  {
    path: '/product/cart',
    name: 'ShoppingCart',
    component: () => import('../views/product/ShoppingCart.vue')
  },
  {
    path: '/product/detail/:id?',
    name: 'ProductDetail',
    component: () => import('../views/product/ProductDetail.vue')
  },
  {
    path: '/product/wishlist',
    name: 'ProductWishlist',
    component: () => import('../views/product/ProductWishlist.vue')
  },
  {
    path: '/product/order',
    name: 'AccountOrder',
    component: () => import('../views/product/AccountOrder.vue')
  },
  {
    path: '/product/comment',
    name: 'PostComment',
    component: () => import('../views/product/PostComment.vue')
  },
  {
    path: '/mem/login',
    name: 'Login',
    component: () => import('../views/mem/Login.vue')
  },
  {
    path: '/mem/agree',
    name: 'Agree',
    component: () => import('../views/mem/Agree.vue')
  }
]

const router = createRouter({
  history: createWebHistory('/'),
  routes
})

export default router
