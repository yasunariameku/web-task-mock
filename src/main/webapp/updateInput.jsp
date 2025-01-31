<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <div class="header">
    <h1 class="site_logo"><a href="menu.html">商品管理システム</a></h1>
    <div class="user">
      <p class="user_name">佐藤さん、こんにちは</p>
      <form class="logout_form" action="logout.html" method="get">
        <button class="logout_btn" type="submit">
          <img src="images/ドアアイコン.png">ログアウト</button>
      </form>
    </div>
  </div>

  <hr>

  <div class="insert">
    <div class="form_body">
    <span class="error"><c:if test="${not empty msg}"><p class="error">${msg}</p></c:if></span>
      <form action="UpdateServlet" method="post">
        <fieldset class="label-130">
          <div>
            <label>商品ID</label>
            <input type="text" name="productId" value="${productDetail.getProduct_id()}" class="base-text">
            <span class="error"><c:if test="${not empty id_msg}"><p class="error">${id_msg}</p></c:if></span>
          </div>
          <div>
            <label>商品名</label>
            <input type="text" name="productName" value="${productDetail.getName()}" class="base-text">
            <span class="error"><c:if test="${not empty name_msg}"><p class="error">${name_msg}</p></c:if></span>
          </div>
          <div>
            <label>単価</label>
            <input type="text" name="price" value="${productDetail.getPrice()}" class="base-text">
            <span class="error"><c:if test="${not empty price_msg}"><p class="error">${price_msg}</p></c:if></span>
          </div>
          <div>
            <label>カテゴリ</label> <select name="category" class="base-text">
              <option value="1">筆記具</option>
              <option value="2">オフィス機器</option>
              <option value="3">事務消耗品</option>
              <option value="4">紙製品</option>
              <option value="5">雑貨</option>
            </select>
          </div>
          <div>
            <label>商品説明</label>
            <textarea name="description" class="base-text">${productDetail.getDescription()}</textarea>
          </div>
          <div>
            <label>画像</label>
            <input type="file" name="file">
            <span class="error">エラーメッセージ</span>
          </div>
        </fieldset>
          <div class="btns">
            <button type="button" onclick="openModal()" class="basic_btn">更新</button>
            <input type="button" onclick="location.href='Search?search='" value="メニューに戻る" class="cancel_btn">
          </div>
          <div id="modal">
            <p class="modal_message">更新しますか？</p>
            <div class="btns">
              <button type="submit" class="basic_btn">更新</button>
              <button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
            </div>
          </div>
      </form>
    </div>
  </div>
  <div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>