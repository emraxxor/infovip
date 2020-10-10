import VueRouter from 'vue-router';
import VueMoment from 'vue-moment'

window._ = require('lodash');

try {
    window.Vue = require('vue').default;
    Vue.use(VueRouter);
    Vue.use(VueMoment);
} catch (e) {
    console.error(e);
}