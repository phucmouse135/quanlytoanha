<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/11/2024
  Time: 4:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/admin/building-list"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Danh sách tòa nhà </title>
    <style>
        .widget-box {
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 15px;
            background: #fff;
            box-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);
        }

        .widget-header {
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }

        .widget-title {
            font-size: 18px;
            margin: 0;
        }

        .widget-main {
            margin-top: 15px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-check-input {
            margin-top: 0.3rem;
        }

        .btn_search {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Dashboard
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-12 widget-container-col ui-sortable">
                    <div class="widget-box">
                        <div class="widget-header ui-sortable-handle">
                            <h5 class="widget-title">Tìm Kiếm</h5>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main" id="form_list">
                                <form:form action="${formUrl}" id="listForm" method="get" modelAttribute="modalSearch">
                                    <div class="form-row">
                                        <div class="form-group">
                                            <div class="col-sm-6">
                                                <label for="name">Tên tòa nhà: </label>
                                                <form:input type="text" class="form-control" id="name" name="name"
                                                            path="name"/>
                                            </div>
                                            <div class="col-sm-6">
                                                <label for="floorArea">Diện tích sàn: </label>
                                                <form:input type="text" class="form-control" id="floorArea"
                                                            name="rentarea" path="floorArea"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <label for="district">Quận </label>
                                                <form:select id="district" class="form-control" path="district">
                                                    <form:option value="">Choose...</form:option>
                                                    <form:options items="${listDistrict}"/>
                                                </form:select>
                                            </div>
                                            <div class="col-sm-3">
                                                <label for="ward">Phường</label>
                                                <form:input type="text" id="ward" class="form-control" name="ward"
                                                            path="ward"/>
                                            </div>
                                            <div class="col-sm-3">
                                                <label for="street">Đường </label>
                                                <form:input type="text" class="form-control" id="street" name="street"
                                                            path="street"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <label for="numberOfBasement">Số tầng hầm </label>
                                                <form:input type="text" id="numberOfBasement" class="form-control"
                                                            name="numberOfBasement" path="numberOfBasement"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="direction">Hướng </label>
                                                <form:input type="text" id="direction" class="form-control"
                                                            name="direction" path="direction"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="level">Hạng </label>
                                                <form:input type="text" id="level" class="form-control" name="level"
                                                            path="level"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group">
                                            <div class="col-sm-3">
                                                <label for="rentAreaFrom">Diện tích từ </label>
                                                <form:input type="text" class="form-control" id="rentAreaFrom"
                                                            name="rentAreaFrom" path="areaFrom"/>
                                            </div>
                                            <div class="col-sm-3">
                                                <label for="rentAreaTo">Diện tích đến </label>
                                                <form:input type="text" class="form-control" id="rentAreaTo"
                                                            name="rentAreaTo" path="areaTo"/>
                                            </div>
                                            <div class="col-sm-3">
                                                <label for="rentPriceFrom">Giá từ</label>
                                                <form:input type="text" class="form-control" id="rentPriceFrom"
                                                            name="rentPriceFrom" path="rentPriceFrom"/>
                                            </div>
                                            <div class="col-sm-3">
                                                <label for="rentPriceTo">Giá đến </label>
                                                <form:input type="text" class="form-control" id="rentPriceTo"
                                                            name="rentPriceTo" path="rentPriceTo"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group">
                                            <div class="col-sm-4">
                                                <label for="managerName">Tên quản lí </label>
                                                <form:input type="text" class="form-control" id="managerName"
                                                            name="managerName" path="managerName"/>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="managerPhone">Số điện thoại quản lí </label>
                                                <form:input type="text" class="form-control" id="managerPhone"
                                                            name="managerPhone" path="managerPhone"/>
                                            </div>
                                            <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                                                <div class="col-sm-4">
                                                    <label for="employees">Nhân viên phụ trách </label>
                                                    <form:select id="employees" name="employees" class="form-control" path="staffId">
                                                        <form:option value="">Choose...</form:option>
                                                        <form:options items="${listStaffs}"/>
                                                    </form:select>
                                                </div>
                                            </security:authorize>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-sm-5">
                                                <label for="typeCode">Loại tòa nhà:       </label>
                                                <form:checkboxes items="${listBuildingTypes}" path="typeCode" />
                                            </div>
                                        </div>
                                    </div>
                                    <button id="btnSearchBuilding" type="submit" class="btn btn-success btn_search">
                                        <i class="ace-icon fa fa-search"></i>
                                        Tìm kiếm
                                    </button>
                                </form:form>
                            </div>
                        </div>
                        <div class="widget-footer pull-right" style="margin: 10px;">
                            <a href='/admin/building-edit' class="btn btn-success" title="ADD">
                                <i class="bi bi-building-add"></i>ADD
                            </a>
                            <a href="javascript:void(0)" class=" btn btn-primary" title="delete"  id="btn-delete-all">
                                <i class="bi bi-building-dash"></i>DELETE
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="table-header">
                Danh sách tòa nhà
            </div>
            <div>
                <display:table name="buildingSearchResponses" requestURI="/admin/building-list" pagesize="5" id="building">
                    <display:column title="Select">
                        <input type="checkbox" name="checkList" value="${building.id}" class="ace">
                        <span class="lbl"></span>
                    </display:column>
                    <display:column property="name" title="Tên tòa nhà" sortable="true"/>
                    <display:column property="address" title="Địa chỉ" sortable="true"/>
                    <display:column property="numberOfBasement" title="Số tầng hầm" sortable="true"/>
                    <display:column property="managerName" title="Tên quản lí" sortable="true"/>
                    <display:column property="managerPhone" title="Số điện thoại" sortable="true"/>
                    <display:column property="floorArea" title="DT sàn" sortable="true"/>
                    <display:column property="rentArea" title="DT thuê" sortable="true"/>
                    <display:column property="rentPrice" title="Giá Thuê" sortable="true"/>
                    <display:column property="servicePrice" title="Giá dịch vụ" sortable="true"/>
                    <display:column property="brokerageFee" title="Phí môi giới" sortable="true"/>
                    <display:column title="Thao tác">
                        <div class="hidden-sm hidden-xs action-buttons">
                            <a class="blue" href="javascript:void(0)" onclick="assignmentBuilding(${building.id})">
                                <i class="ace-icon fa fa-user bigger-130"></i>
                            </a>
                            <a class="green" href="/admin/building-edit?id=${building.id}">
                                <i class="ace-icon fa fa-pencil bigger-130"></i>
                            </a>
                            <a class="red" href="javascript:void(0)" onclick="deleteBuilding(${building.id})">
                                <i class="ace-icon fa fa-trash-o bigger-130"></i>
                            </a>
                        </div>
                    </display:column>
                    <display:setProperty name="paging.banner.placement" value="bottom"/>
                    <display:setProperty name="export" value="true"/>
                </display:table>
            </div>

        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="assignmentBuildingModel" tabindex="-1" role="dialog"
     aria-labelledby="assignmentBuildingModelLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="assignmentBuildingModelLabel">Assignment Building</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn</th>
                        <th>Tên Nhân Viên</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <!-- hidden input  -->
                <input type="hidden" name="buildingId" value="" id="buildingId">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="btnAssignmentBuilding">Giao Tòa Nhà</button>
                <%--                <button type="button" class="btn btn-primary" id="btnSaveAssignmentBuilding">Save changes</button>--%>
            </div>
        </div>
    </div>
</div>
<script>
    function assignmentBuilding(id) {
        $('#assignmentBuildingModel').modal('show');
        $('#buildingId').val(id);
        loadStaffs(id);
    }

    function loadStaffs(buildingId) {
        $.ajax({
            url: '/api/building/' + buildingId + '/staffs',
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            success: function (data){
                var html = '';
                data.forEach(function (item) {
                    html += '<tr>';
                    if (item.checked) {
                        html += '<td><input type="checkbox" name="selectEmployee" value="' + item.staffId + '" checked></td>';
                    } else {
                        html += '<td><input type="checkbox" name="selectEmployee" value="' + item.staffId + '"></td>';
                    }
                    html += '<td>' + item.fullName + '</td>';
                    html += '</tr>';
                });
                $('#staffList tbody').html(html);
            }
        });
    }

    $('#btnAssignmentBuilding').click(function () {
        var data = {};
        var staffs = [];
        $('input[name="selectEmployee"]:checked').each(function () {
            staffs.push($(this).val());
        });
        data.buildingId = $('#buildingId').val();
        data.staffs = staffs;
        $.ajax({
            url: '/api/building/assignment',
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (response) {
                alert('Assignment building successfully');
                window.location.reload();
            },
            error: function (error) {
                alert('Assignment building successfully');
                window.location.reload();
            }
        });
    });

    $('#btnSearchBuilding').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    })
    function deleteBuilding(id) {
        var list = [id];
        deleteBuildings(list);
    }
    $('#btn-delete-all').click(function () {
        var buildingId = [];
        $('input[name="checkList"]:checked').each(function () {
            buildingId.push($(this).val());
        })
        deleteBuildings(buildingId);
    })

    function deleteBuildings(data) {
        if (confirm('Are you sure you want to delete this building?')) {
            $.ajax({
                url: '/api/building/' + data,
                type: 'DELETE',
                data: JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json',
                success: function (data){
                    alert('Building deleted successfully');
                    location.reload();
                },
                error: function (error) {
                    alert('Building deleted successfully');
                    location.reload();
                }
            });
        }
    }
</script>
</body>
</html>
