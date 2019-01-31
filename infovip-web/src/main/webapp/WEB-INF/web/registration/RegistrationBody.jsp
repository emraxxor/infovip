<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/tlds/module-manager" prefix="module"%>
<%@ taglib uri="/tlds/translate" prefix="tr"%>

<section class="page--wrapper pt--80 pb--20">
            <div class="container">
                <div class="row">
                    <!-- Main Content Start -->
                    <div class="main--content col-md-12">
                        <div class="main--content-inner">
                          
                            <!-- Registration Form Start -->
                            <div class="checkout--form pt--30 fs--14">
                                <form name="registration">
                                    <div class="row">
                                        <div class="col-md-6 pb--40">
                                            <h3 class="h4 pb--30"><tr:tr val="Registration"></tr:tr></h3>

                                            <div class="row">
                                            	<div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span><tr:tr val="Nick Name"></tr:tr> *</span>
                                                            <input type="text" name="userName" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>
                                            
                                                <div class="col-xs-6 col-xxs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>First Name *</span>
                                                            <input type="text" name="firstName" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-xs-6 col-xxs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>Last Name *</span>
                                                            <input type="text" name="lastName" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>
                                                
                                                 <div class="col-xs-6 col-xxs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>Password *</span>
                                                            <input type="password" name="userPassword" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-xs-6 col-xxs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>Password repeat *</span>
                                                            <input type="password" name="userPasswordRepeat" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>E-mail *</span>
                                                            <input type="email" name="userMail" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>Country *</span>
                                                            <input type="text" name="country" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>
                                                
                                                 <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>County *</span>
                                                            <input type="text" name="county" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>
                                                
                                                
												<div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>City *</span>
                                                            <input type="text" name="city" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>
                                             
                                             	<div class="col-xs-12">
                                             		<div style="margin: 0 auto">
														<div class="g-recaptcha" data-sitekey="6LfkM40UAAAAALw5xeC2104gDB6ekEgNYkwmW_7V"></div>
													</div>
		                                            <button type="button" class="btn btn-primary"><tr:tr val="Submit"></tr:tr></button>
        		                                </div>
                                               
                                            </div>
                                        </div>
                             
                                    </div>
                                </form>
                            </div>
                            <!-- Checkout Form End -->
                        </div>
                    </div>
                    <!-- Main Content End -->
                </div>
            </div>
        </section>