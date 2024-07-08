/**
* 2007-2018 PrestaShop
*
* NOTICE OF LICENSE
*
* This source file is subject to the Academic Free License (AFL 3.0)
* that is bundled with this package in the file LICENSE.txt.
* It is also available through the world-wide-web at this URL:
* http://opensource.org/licenses/afl-3.0.php
* If you did not receive a copy of the license and are unable to
* obtain it through the world-wide-web, please send an email
* to license@prestashop.com so we can send you a copy immediately.
*
* DISCLAIMER
*
* Do not edit or add to this file if you wish to upgrade PrestaShop to newer
* versions in the future. If you wish to customize PrestaShop for your
* needs please refer to http://www.prestashop.com for more information.
*
*  @author PrestaShop SA <contact@prestashop.com>
*  @copyright  2007-2018 PrestaShop SA

*  @license    http://opensource.org/licenses/afl-3.0.php  Academic Free License (AFL 3.0)
*  International Registered Trademark & Property of PrestaShop SA
*/

/*
 * jQuery Parallax js
 */!function(t){var o={init:function(o){var e={offset:!0,bgfixed:!0,invert:!0};return this.each(function(){function n(){p=s.data("source-url"),g=parseFloat(s.data("source-width")),h=parseFloat(s.data("source-height")),s.css({"background-image":"url("+p+")"}),r()}function r(){u.on("scroll",function(){e.offset&&i()}).trigger("scroll"),u.on("resize",function(){s.css({}),e.offset&&l()}).trigger("resize")}function i(){c()}function l(){c()}function c(){var t,o,n,r;f=s.outerHeight(),windowHeight=u.height(),a=s.offset().top,t=d.scrollTop(),o=t+windowHeight,n=t-f,o>a&&a>n&&(r=o-n,pixelScrolled=t-(a-windowHeight),percentScrolled=pixelScrolled/r,e.invert?(deltaTopScrollVal=100*percentScrolled,s.css({"background-position":"50% "+deltaTopScrollVal+"%"})):(deltaTopScrollVal=100*(1-percentScrolled),s.css({"background-position":"50% "+deltaTopScrollVal+"%"})))}o&&t.extend(e,o);{var a,s=t(this),u=t(window),d=t(document),f=0,p="",g="",h="";Boolean(navigator.userAgent.match(/MSIE ([8]+)\./))}n()})},destroy:function(){},reposition:function(){},update:function(){}};t.fn.sitManParallex=function(e){return o[e]?o[e].apply(this,Array.prototype.slice.call(arguments,1)):"object"!=typeof e&&e?void t.error("Method with name "+e+" is not exist for jQuery"):o.init.apply(this,arguments)}}(jQuery);