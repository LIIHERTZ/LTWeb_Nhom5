<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<style>
  /* Tổng quan */
  .page-background {
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #0a1e5e; /* Giống nền trang home */
  }

  /* Container chính */
  .payment-container {
    background-color: #ffffff; /* Màu trắng sáng */
    padding: 1.5rem;
    max-width: 600px;
    margin: 0 auto;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
  }

  /* SVG Icon */
  .payment-icon {
    color: #16a34a; /* Màu xanh tương tự */
    width: 4rem;
    height: 4rem;
    margin: 1.5rem auto;
    display: block;
  }

  /* Tiêu đề */
  .payment-title {
    font-size: 1.5rem;
    color: #1a202c; /* Màu xám đậm */
    font-weight: 600;
    text-align: center;
  }

  /* Văn bản mô tả */
  .payment-description {
    color: #4a5568; /* Màu xám nhạt hơn */
    font-size: 1.125rem;
    margin: 0.5rem 0;
    text-align: center;
  }

  /* Nút trở về */
  .payment-button-container {
    padding: 2.5rem 0;
    text-align: center;
  }

  .payment-button {
    font-size: 1.125rem;
    padding: 0.75rem 3rem;
    background-color: #4f46e5; /* Màu xanh tím */
    color: #ffffff;
    font-weight: 600;
    text-decoration: none;
    border-radius: 4px;
    transition: background-color 0.2s ease;
  }

  .payment-button:hover {
    background-color: #4338ca; /* Màu hover */
  }
</style>
<div class="page-background">
  <div class="payment-container">
    <svg viewBox="0 0 24 24" class="payment-icon">
      <path fill="currentColor"
            d="M12,0A12,12,0,1,0,24,12,12.014,12.014,0,0,0,12,0Zm6.927,8.2-6.845,9.289a1.011,1.011,0,0,1-1.43.188L5.764,13.769a1,1,0,1,1,1.25-1.562l4.076,3.261,6.227-8.451A1,1,0,1,1,18.927,8.2Z">
      </path>
    </svg>

    <div class="text-center">
      <h3 class="payment-title">
        <c:choose>
          <c:when test="${success}">
            Payment Successful!
          </c:when>
          <c:otherwise>
            Payment Failed
          </c:otherwise>
        </c:choose>
      </h3>

      <p class="payment-description">
        <c:choose>
          <c:when test="${success}">
            Your payment was successfully processed. Thank you for your purchase!
          </c:when>
          <c:otherwise>
            ${errorMessage}
          </c:otherwise>
        </c:choose>
      </p>

      <div class="payment-button-container">
        <c:choose>
          <c:when test="${success}">
            <!-- Chuyển đến chi tiết đơn hàng -->
            <a href="${pageContext.request.contextPath}/detailOrder?orderID=${orderID}" class="payment-button">
              Confirm
            </a>
          </c:when>
          <c:otherwise>
            <!-- Quay lại trang checkout -->
            <a href="${pageContext.request.contextPath}/checkout" class="payment-button">
              Return
            </a>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>
</div>
