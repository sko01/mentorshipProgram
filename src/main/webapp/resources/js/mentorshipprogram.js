function getEmployees(){
	$.ajax({
		type:'GET',
		url:'api/employees',
		headers:{
			Accept:"application/json; charset=utf-8",
			"Content-Type":"application/json; charset=utf-8"
		},
		success:function(result){
			var employeeList = result;
			var employeeElement = "<tbody>";
			for(var i=0; i<employeeList.length; i++){
				employeeElement += "<tr>" + "<td>" + employeeList[i].employeeId + "</td>"
								+ "<td>" + employeeList[i].employeeName + "</td>"
								+ "<td>" + employeeList[i].employeeSecondName + "</td>"
								+ "<td>" + employeeList[i].employeeEmail + "</td>"
								+ "<td>" + employeeList[i].employeeBirthday + "</td>"
								+ "<td>" + "<button onclick='updateEmployee(" + employeeList[i].employeeId + ")'>Update</button>" + "</td>"
								+ "<td>" + "<button onclick='deliteEmployee(" + employeeList[i].employeeId + ")'>Delete</button>" + "</td>" + "</tr>";
			}
			employeeElement += "</tbody>";
			document.getElementById("employeeList").innerHTML = employeeElement;
		}
	});
};

function getEmployee() {
	var id = getEmployeeIdFromUrl();
	$.ajax({
		type:'GET',
		url:'../api/employee/' + id,
		headers:{
			Accept:"application/json; charset=utf-8",
			"Content-Type":"application/json; charset=utf-8"
		},
		success:function(result){
			var employee = result;
			var employeeElement = "<table>";
			employeeElement += "<thead><tr><td colspan='2'>" + employee.employeeName + " " + employee.employeeSecondName + "</td></tr></thead>";
			employeeElement += "<tbody>";
			employeeElement += "<tr><td>Name</td><td>Second name</td></tr>";
			employeeElement += "<tr><td><input type='text' value=" + employee.employeeName + " name='employeeName' /></td>";
			employeeElement += "<td><input type='text' value=" + employee.employeeSecondName + " name='employeeSecondName' /></td></tr>";
			employeeElement += "<tr><td>Email</td><td>Birthday</td></tr>";
			employeeElement += "<tr><td><input type='email' value=" + employee.employeeEmail + " name='employeeEmail' /></td>";
			employeeElement += "<td><input type='date' value=" + employee.employeeBirthday + " name='employeeBirthday' /></td></tr>";
			employeeElement += "</tbody>";
			employeeElement += "<tfoot><tr><td><input type='button' value='Save' onclick='saveEmployee(" + employee.employeeId + ")' /></td>";
			employeeElement += "<td><input type='button' value='Cancel' onclick='cancelEmployeeUpdate()' /></td></tr></tfoot>";
			employeeElement += "</table>";
			
			document.getElementById("employeeInfo").innerHTML = employeeElement;
//			location.reload();
		}
	});
};

function saveEmployee(employeeId) {
	var formData = $('#updateEmployeeForm').serializeObject();
	var resultJson = JSON.stringify(formData, null, 2);
	
	$.ajax({
		type:'POST',
		url:'../api/update/' + employeeId,
		data:resultJson,
		headers:{
			Accept:"application/json; charset=utf-8",
			"Content-Type":"application/json; charset=utf-8"
		}
	})
	
	window.location.href = "../";
};

function deliteEmployee(employeeId) {
	$.ajax({
		type:'DELETE',
		url:'api/delete/' + employeeId,
		headers:{
			Accept:"application/json; charset=utf-8",
			"Content-Type":"application/json; charset=utf-8"
		},
		success:function(result){
			location.reload();
		}
	})
};

function addEmployee () {
	var formData = $('#newEmployeeForm').serializeObject();
	var resultJson = JSON.stringify(formData, null, 2);
	
	$.ajax({
		type:'POST',
		url:'api/add',
		data:resultJson,
		headers:{
			Accept:"application/json; charset=utf-8",
			"Content-Type":"application/json; charset=utf-8"
		},
		success: function(result){
			location.reload();
		},
	})
};
	
function updateEmployee(employeeId) {
	window.location.href = 'employee/' + employeeId;
};

function cancelEmployeeUpdate() {
	window.location.href = "../";
};
	
function getEmployeeIdFromUrl() {
	var url = window.location.pathname.split("/");
	var employeeIdFromUrl = url[url.length - 1];
	return employeeIdFromUrl;
};

$.fn.serializeObject = function () {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function () {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};