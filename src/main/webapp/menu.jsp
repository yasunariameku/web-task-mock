<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
<link href="css/commons.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
  <div id="app">

    <div class="header">
      <h1 class="site_logo"><a href="menu.jsp">商品管理システム</a></h1>
      <div class="user">
        <p class="user_name">${name} こんにちは</p>
        <form class="logout_form" action="logout" method="get">
          <button class="logout_btn" type="submit">
            <img src="images/ドアアイコン.png">ログアウト</button>
        </form>
      </div>
    </div>

    <hr>

    <div class="btn"><a class="basic_btn regist" href="insert.jsp">新規登録</a></div>
    <c:if test="${not empty msg}"><p class="error">${msg}</p></c:if>
    
    <!-- 検索機能 -->
    <form method="get" action="Search" class="search_container">
      <input type="text" size="25" name="search" value="" placeholder="キーワード検索" >
      <button type="submit">検索</button> 
    </form>

    <table>
        <div class="caption"><p>検索結果：${searchResult}件</p></div>
        <div class="order">
          <select class="base-text">
            <option>並び替え</option>
            <option>商品ID</option>
            <option>カテゴリ</option>
            <option>単価：安い順</option>
            <option>単価：高い順</option>
            <option>登録日：古い順</option>
            <option>登録日：新しい順</option>
          </select>
        </div>
      <thead>
        <tr>
          <th>商品ID</th>
          <th>商品名</th>
          <th>単価</th>
          <th>カテゴリ</th>
          <th>詳細</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="resultA" items="${result}" varStatus="status">
          <tr>
            <td><c:out value="${resultA.product_id}"/></td>
            <td><c:out value="${resultA.name}"/></td>
            <td><c:out value="${resultA.price}"/></td>
            <td><c:out value="${resultA.category}"/></td>
            <td><a class="detail_btn" href="DitailServlet?id=${resultA.id}">詳細</a></td>
          </tr>
		</c:forEach>
      </tbody>
    </table>
  </div>
  <footer></footer>

  <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
  <script>
    const vie = new Vue({
      el: "#app",
      data: {
        products: [
          {
            ID: "10001",
            name: "マッキー(黒)",
            price: 160,
            category: "筆記具",
          },
          {
            ID: "10002",
            name: "電卓",
            price: 935,
            category: "オフィス機器",
          },
          {
            ID: "10003",
            name: "ホッチキス芯",
            price: 220,
            category: "事務消耗品",
          },
          {
            ID: "10004",
            name: "Campus(5冊組)",
            price: 220,
            category: "紙製品",
          },
          {
            ID: "10005",
            name: "地球儀",
            price: 160,
            category: "雑貨",
          },
          {
            ID: "10006",
            name: "ロジカルノート(5冊組)",
            price: 386,
            category: "紙製品",
          },
          {
            ID: "10007",
            name: "レジスター",
            price: 130,
            category: "オフィス用品",
          },
          {
            ID: "10008",
            name: "カドケシ",
            price: 150,
            category: "筆記具",
          },
          {
            ID: "10009",
            name: "アラビックヤマト",
            price: 200,
            category: "事務用品",
          },
          {
            ID: "10010",
            name: "粘土",
            price: 160,
            category: "雑貨",
          },
        ]
      }
    })

  </script>
</body>
</html>