import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import App from './App.vue'
import router from './router'
import 'element-plus/theme-chalk/index.css'
import CKEditor from '@ckeditor/ckeditor5-vue';

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(CKEditor)
app.use(ElementPlus)

app.mount('#app')
