<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:set var="contextPath" value="/admin/building-edit"/>
<html>
<head>
    <title>Title</title>
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
                            <h5 class="widget-title">ADD / EDIT</h5>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form action="${contextPath}" modelAttribute="buildingEdit" method="get"
                                           id="form_edit">
                                    <form:input type="hidden" name="id" value="" path="id"/>
                                    <div class="form-group row">
                                        <label for="name" class="col-sm-2 col-form-label">Tên tòa
                                            nhà</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="name"
                                                        name="name" value="${buildingEdit.name}" path="name"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="district" class="col-sm-2 col-form-label">Quận</label>
                                        <div class="col-sm-10">
                                            <form:select name="district" id="district" class="form-control"
                                                         path="district">
                                                <form:option value="">Chọn quận</form:option>
                                                <form:options items="${listDistrict}"/>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="ward" class="col-sm-2 col-form-label">Phường</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="ward" name="ward"
                                                        path="ward"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="street" class="col-sm-2 col-form-label">Đường</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="street" name="street"
                                                        path="street"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="structure" class="col-sm-2 col-form-label">Kết cấu</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="structure" name="structure"
                                                        path="structure"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="numberOfBasement" class="col-sm-2 col-form-label">Số tầng
                                            hầm</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" class="form-control" id="numberOfBasement"
                                                        name="numberOfBasement" path="numberOfBasement"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="floorArea" class="col-sm-2 col-form-label">Diện tích
                                            sàn</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" class="form-control" id="floorArea"
                                                        name="floorArea" path="floorArea"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="direction" class="col-sm-2 col-form-label">Hướng</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="direction" name="direction"
                                                        path="direction"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="level" class="col-sm-2 col-form-label">Hạng</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="level" name="level"
                                                        path="level"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="rentArea" class="col-sm-2 col-form-label">Diện tích
                                            thuê</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="rentArea" name="rentArea"
                                                        value="${buildingEdit.rentArea}" path="rentArea"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="rentPrice" class="col-sm-2 col-form-label">Giá
                                            Thuê</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" class="form-control" id="rentPrice"
                                                        name="rentPrice" path="rentPrice"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="rentPriceDescription" class="col-sm-2 col-form-label">Mô tả
                                            giá</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="rentPriceDescription"
                                                        name="rentPriceDescription" path="rentPriceDescription"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="serviceFee" class="col-sm-2 col-form-label">Phí dịch
                                            vụ</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" class="form-control" id="serviceFee"
                                                        name="serviceFee" path="serviceFee"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="carFee" class="col-sm-2 col-form-label">Phí ô tô</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" class="form-control" id="carFee" name="carFee"
                                                        path="carFee"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="overtimeFee" class="col-sm-2 col-form-label">Phí ngoài
                                            giờ</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" class="form-control" id="overtimeFee"
                                                        name="overTimeFee" path="overTimeFee"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="electricityFee" class="col-sm-2 col-form-label">Tiền
                                            điện</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" class="form-control" id="electricityFee"
                                                        name="electricityFee" path="electricityFee"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="deposit" class="col-sm-2 col-form-label">Đặt cọc</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" class="form-control" id="deposit" name="deposit"
                                                        path="deposit"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="payment" class="col-sm-2 col-form-label">Thanh toán</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="payment" name="payment"
                                                        path="payment"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="leaseTerm" class="col-sm-2 col-form-label">Thời hạn thuê</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="leaseTerm" name="modifiedDate"
                                                        path="modifiedDate"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="decorationTime" class="col-sm-2 col-form-label">Thời gian trang
                                            trí</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="decorationTime"
                                                        name="decorationTime" path="decorationTime"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label">Tên quản lí</label>
                                        <div class="col-sm-10">
                                            <form:select name="managerName" id="managerName" class="form-control"
                                                         path="managerName">
                                                <form:option value="">Chọn nhân viên quản lí</form:option>
                                                <form:options items="${listStaffs}"/>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="managerPhone" class="col-sm-2 col-form-label">SĐT quản lí</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="managerPhone"
                                                        name="managerPhone" path="managerPhone"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label">Loại tòa nhà</label>
                                        <div class="col-sm-10">
                                            <c:forEach var="typeBuilding" items="${listBuildingTypes}">
                                                <label>
                                                    <form:checkbox value="${typeBuilding.key}" name="typeCodes" path="typeCodes"/>
                                                        ${typeBuilding.value}
                                                </label>
                                            </c:forEach>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="notes" class="col-sm-2 col-form-label">Ghi chú</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" class="form-control" id="notes" name="note"
                                                        path="note"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label">Hình đại diện</label>
                                        <input class="col-sm-3 no-padding-right" type="file" id="uploadImage">
                                        <div class="col-sm-10">
                                            <c:if test="${!empty buildingEdit.avatar}">
                                                <c:set var="avatarPath" value="/reppsitory${buildingEdit.avatar}"/>
                                                <img src="${avatarPath}" alt="avatar" width="100px" height="100px">
                                            </c:if>
                                            <c:if test="${empty buildingEdit.avatar}">
                                                <img src="/resources/images/no-image.png" alt="avatar" width="100px"
                                                     height="100px">
                                            </c:if>
                                        </div>
                                    </div>
                                    <c:if test="${!empty buildingEdit.id}">
                                        <a href="/admin/building-list" type="submit" id="btn_add_building"
                                           class="btn btn-success">
                                            <i class="ace-icon fa fa-save"></i>
                                            SAVE
                                        </a>
                                        <a href="/admin/building-list" type="submit" id="btn_cancle"
                                           class="btn btn-success">
                                            <i class="ace-icon fa fa-save"></i>
                                            CANCLE
                                        </a>
                                    </c:if>

                                    <c:if test="${empty buildingEdit.id}">
                                        <a href="/admin/building-list" type="submit" id="btn_add_building"
                                           class="btn btn-success">
                                            <i class="ace-icon fa fa-save"></i>
                                            ADD
                                        </a>
                                        <a href="/admin/building-list" type="submit" id="btn_cancle"
                                           class="btn btn-success">
                                            <i class="ace-icon fa fa-save"></i>
                                            CANCLE
                                        </a>
                                    </c:if>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- /.page-content -->

<script>
    var avatar = '';
    var avatarName = '';

    // Xử lý sự kiện khi người dùng chọn file ảnh
    $('#uploadImage').change(function () {
        var file = this.files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
            avatar = e.target.result;
            avatarName = file.name;
        };
        reader.readAsDataURL(file);
    });


    const btn_add_building = document.getElementById('btn_add_building');

    btn_add_building.addEventListener('click', (e) => {
        // Hiển thị hộp thoại xác nhận và kiểm tra phản hồi của người dùng
        if (confirm('Bạn có chắc chắn muốn thêm mới / sửa không?')) {
            // Ngăn form gửi dữ liệu theo cách mặc định
            e.preventDefault();

            // Chuẩn bị dữ liệu từ form
            var data = {};
            var typeCodes = [];
            var formData = $('#form_edit').serializeArray();

            // Xử lý dữ liệu từ form
            $.each(formData, function (index, field) {
                if (field.name === 'typeCodes') {
                    typeCodes.push(field.value);
                } else {
                    data[field.name] = field.value;
                }
                if('' !== avatar) {
                    data['avatar'] = avatar;
                    data['avatarName'] = avatarName;
                }
            });

            data['typeCodes'] = typeCodes;
            $('#loading_image').show();
            console.log(data);
            if(typeCodes != '') {
                addOrUpdateBuilding(data);
            }
            else{
                window.location.href = "<c:url value='/admin/building-edit?typeCode=required'/>";
            }

        } else {
            // Nếu người dùng chọn Cancel, ngăn form gửi dữ liệu
            e.preventDefault();
        }
    });

    function addOrUpdateBuilding(data) {
        $.ajax({
            type: 'POST',
            url: '/api/building/',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (response) {
                window.location.href = "<c:url value='/admin/building-list?message=success'/>";
            },
            error: function (response) {
                alert('Có lỗi xảy ra, vui lòng thử lại sau');
                <%--window.location.href = "<c:url value='/admin/building-edit?message=error'/>";--%>
            }
        });
    }



    $("#btn_cancle").click(function () {
        window.location.href = "/admin/building-list";
    });


</script>
</body>
</html>
