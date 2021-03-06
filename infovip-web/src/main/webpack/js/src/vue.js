import VueRouter from 'vue-router'
import Vuex from 'vuex'
import VueMoment from 'vue-moment'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap-vue/dist/bootstrap-vue.css'

try {
    window.Vue = require('vue').default;
    Vue.use(BootstrapVue)
    Vue.use(IconsPlugin)
    Vue.use(VueRouter);
    Vue.use(Vuex)
    Vue.use(VueMoment);
} catch (e) {
    console.error(e);
}