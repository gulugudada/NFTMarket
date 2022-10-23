import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axios from 'axios'
import { ethers } from "ethers"
import { ElMessage } from 'element-plus'

const app = createApp(App)
installElementPlus(app)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.config.globalProperties.$axios = axios
app.config.globalProperties.$ethers = ethers
app.config.globalProperties.$message = ElMessage
app.use(store).use(router).mount('#app')