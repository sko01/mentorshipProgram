<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
<%@ page isELIgnored="false" %>
<script type="text/javascript" src="js/jquery-3.1.0.js"></script>
<script type="text/javascript">
function addRowHandlers() {
    var table = document.getElementById("tableId");
    var rows = table.getElementsByTagName("tr");
    for (i = 0; i < rows.length; i++) {
        var currentRow = table.rows[i];
        var createClickHandler = 
            function(row) 
            {
                return function() { 
                                        var cell = row.getElementsByTagName("td")[0];
                                        var id = cell[1].innerHTML;
                                        alert("id:" + id);
                                 };
            };

        currentRow.onclick = createClickHandler(currentRow);
    }
}
window.onload = addRowHandlers();
</script>
</head>
<body>
 	<h1>Employees</h1>
	<table width="80%">
		<tbody>
		<c:forEach items="${employees}" var="element">
			<tr>
				<td style="border-bottom:1px solid black;">${element.employeId}</td>
				<td style="border-bottom:1px solid black;">${element.employeName}</td>
				<td style="border-bottom:1px solid black;">${element.employeSecondName}</td>
				<td style="border-bottom:1px solid black;">${element.employeEmail}</td>
				<td style="border-bottom:1px solid black;">${element.employeBirthday}</td>
				<td style="border-bottom:1px solid black;">
					<form action="update/${element.employeId}" method="post"><input type="submit" value="Update"/></form>
				</td>
				<td style="border-bottom:1px solid black;">
					<form action="delete/${element.employeId}" method="post"><input type="submit" value="Delete"/></form>
				</td>
			</tr>
		</c:forEach>
		</tbody>
 	</table>
 	<br>
 	<div>
 		<h3>Add new employe</h3>
 		<form action="add" method="post">
 			Name
 			<input type="text" name="name" />
 			Second name
 			<input type="text" name="secondName" />
 			<br>
 			Email
 			<input type="email" name="email" />
 			Birthday(dd/mm/yyyy)
 			<input type="date" name="birthday" />
 			<br>
 			<input type="submit" name="submit" value="Add employe" />
 		</form>
 	</div>
</body>
</html>
