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
                          
                            <!-- Login Form Start -->
                            <div class="checkout--form pt--30 fs--14">
                            
                           
                                <form action="${pageContext.servletContext.contextPath}/login" method="post">
                                    <div class="row">
                                        <div class="col-md-6 pb--40">
                                            <h3 class="h4 pb--30"><tr:tr val="Login"></tr:tr></h3>

                                            <div class="row">
                                            
                                            
                                            	  <c:if test="${not empty param.err and ( param.err == 'invalid' or param.err == 'captcha' ) }">
											  		<div class="col-xs-12">
                                                    	<div class="form-group">
                                                        	<label>
                                                        		<tr:tr val="Authentication failed!"></tr:tr>
                                                        	</label>
                                                      	 </div>
                                                    </div>
												  </c:if>	
                                            
                                                
                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>E-mail *</span>
                                                            <input type="email" name="email" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>

												<div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span><tr:tr val="Password"></tr:tr> *</span>
                                                            <input type="password" name="password" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>


                                             	<div class="col-xs-12">
                                             		<div style="margin: 0 auto">
														<div class="g-recaptcha" data-sitekey="6LfkM40UAAAAALw5xeC2104gDB6ekEgNYkwmW_7V"></div>
													</div>
		                                            <button type="submit" class="btn btn-primary"><tr:tr val="Submit"></tr:tr></button>
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