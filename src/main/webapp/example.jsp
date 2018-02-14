<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/27/2018
  Time: 6:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>example.jsp</title>
    <%--Base 'BluePrint' files--%>
    <link rel="stylesheet" href="css/blueprint/screen.css" type="text/css" media="screen, projection" />
    <link rel="stylesheet" href="css/blueprint/print.css" type="text/css" media="print" />
        <!--[if lt IE 8]>
        <link rel="stylesheet" href="css/blueprint/ie.css" type="text/css" media="screen, projection" />
        <![endif]-->

    <%--Plugins to 'BluePrint files'--%>
    <link rel="stylesheet" href="css/blueprint/plugins/buttons/screen.css" type="text.css" media="screen, projection" />
    <link rel="stylesheet" href="css/blueprint/plugins/fancy-type/screen.css" type="text.css" media="screen, projection" />
    <link rel="stylesheet" href="css/blueprint/plugins/link-icons/screen.css" type="text.css" media="screen, projection" />

</head>
<body>

<br/>

<div class="container">
    <h1>link-icons examples</h1>
    <hr>
    <h2 class="???"><em>This sample page demonstrates the link-icons</em> plugin.</h2>
    <hr>
    <div class="span-24 last">
        <table>
            <thead>
            <tr>
                <th>Sample link</th>
                <th>Description</th>
                <th>Regular expression of <code>href</code>anchor attribute</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><a href="http://example.com">An external link</a></td>
                <td>Links that starts with <code>http://</code>.</td>
                <td><code>http://.*</code></td>
            </tr>
            <tr>
                <td><a href="https://example.com">A secure external link</a></td>
                <td>Links that starts with <code>https://</code>.</td>
                <td><code>https://.*</code></td>
            </tr>
            <tr>
                <td><a href="mailto:person@example.com">Mail someone</a></td>
                <td>Links that starts with <code>mailto:</code>.</td>
                <td><code>mailto:.*</code></td>
            </tr>
            <tr>
                <td><a href="http://example.com/document.pdf">A PDF document</a></td>
                <td>Links that ends with <code>.pdf</code>.</td>
                <td><code>*\.pdf</code></td>
            </tr>
            <tr>
                <td><a href="http://example.com/document.doc">A word document</a></td>
                <td>Links that ends with <code>.doc</code>.</td>
                <td><code>*\.doc</code></td>
            </tr>
            <tr>
                <td><a href="http://example.com/workbook.xls">A Microsoft Excel workbook</a></td>
                <td>Links that ends with <code>.xls</code>.</td>
                <td><code>*\.xls</code></td>
            </tr>
            <tr>
                <td><a href="http://example.com/feed.rss">An RSS feed</a></td>
                <td>Links that ends with <code>.rss</code>.</td>
                <td><code>*\.rss</code></td>
            </tr>
            <tr>
                <td><a href="http://example.com/feed.rdf">An RDF feed</a></td>
                <td>Links that ends with <code>.rdf</code>.</td>
                <td><code>*\.rdf</code></td>
            </tr>
            <tr>
                <td><a href="aim:addbuddy?screenname=example">An AIM instant messaging link</a></td>
                <td>Links that starts with <code>aim:</code>.</td>
                <td><code>aim:.*</code></td>
            </tr>
            </tbody>
        </table>
    </div>
    <hr>
</div>

</body>
</html>
