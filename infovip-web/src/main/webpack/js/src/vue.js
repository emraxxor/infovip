window._ = require('lodash');

try {
    window.Vue = require('vue').default;
} catch (e) {
    console.error(e);
}