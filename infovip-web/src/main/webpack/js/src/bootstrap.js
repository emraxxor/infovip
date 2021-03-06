//import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/bootstrap_custom/bootstrap.min.css';
import '../css/bootstrap_custom/bootstrap-theme.min.css';

import 'jquery-ui/themes/base/theme.css';
import 'jquery-ui/ui/core';
import 'jquery-ui/ui/widgets/datepicker';
import 'jquery-ui/ui/widgets/mouse';


try {
    window.Popper = require('popper.js').default;
    window.jQuery = require('jquery'),  require('jquery-ui')
    require('../lib/jquery/jquery.sticky')
    require('../lib/jquery/isotope.pkgd.min')
    require('bootstrap');
} catch (e) {
    console.error(e);
}