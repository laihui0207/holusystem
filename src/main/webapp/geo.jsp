
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>演示：HTML5获取地理位置定位信息</title>
    <meta name="keywords" content="html5,地理位置" />
    <meta name="description" content="Helloweba文章结合实例演示HTML5、CSS3、jquery、PHP等WEB技术应用。" />
    <script type="text/javascript" src="http://libs.useso.com/js/jquery/1.7.2/jquery.min.js"></script>
    <script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&libraries=convertor"></script>
    <link rel="stylesheet" type="text/css" href="../css/main.css" />
    <style type="text/css">
        .demo{width:560px; margin:60px auto 10px auto}
        .geo{margin-top:20px}
        .demo p{line-height:32px; font-size:16px}
        .demo p span,#baidu_geo,#google_geo{font-weight:bold}
    </style>

</head>

<body>

<div id="header">
    <div id="logo"><h1><a href="http://www.helloweba.com" title="返回helloweba首页">helloweba</a></h1></div>
    <div class="demo_topad"><script src="/js/ad_js/demo_topad.js" type="text/javascript"></script></div>
</div>

<div id="main">
    <h2 class="top_title"><a href="http://www.helloweba.com/view-blog-300.html">HTML5获取地理位置定位信息</a></h2>
    <div class="demo">
        <p>地理坐标：<span id="latlon"></span></p>
        <div class="geo">
            <p>百度地图定位位置：</p>
            <p id="baidu_geo"></p>
        </div>
        <div class="geo">
            <p>GOOGLE地图定位位置：</p>
            <p id="google_geo"></p>
        </div>
        <div class="geo">
            <p> qq maps:</p>
            <p id="qq_geo"></p>
        </div>
    </div>
    <div class="ad_76090"><script src="/js/ad_js/bd_76090.js" type="text/javascript"></script></div><br/>
</div>
<div style="width:603px;height:300px" id="container"></div>
<script>
    function getLocation(){
        if (navigator.geolocation){
            navigator.geolocation.getCurrentPosition(showPosition,showError);
        }else{
            alert("浏览器不支持地理定位。");
        }
    }

    function showPosition(position){
        $("#latlon").html("纬度:"+position.coords.latitude +'，经度:'+ position.coords.longitude);

        var map = new qq.maps.Map(document.getElementById("container"), {
            center: new qq.maps.LatLng(position.coords.latitude, position.coords.longitude),
            zoom: 13
        });
        var info= new qq.maps.InfoWindow({map: map});
        //转换百度坐标为腾讯坐标
        qq.maps.convertor.translate(new qq.maps.LatLng(position.coords.latitude, position.coords.longitude), 1, function(res) {
            latlng = res[0];
            var marker = new qq.maps.Marker({
                map: map,
                position: latlng
            });

            geocoder=new qq.maps.Geocoder({complete:function(result){
                info.open();
                info.setContent(result.detail.address)
                info.setPosition(result.detail.location);
                $("#qq_geo").html(result.detail.address);
            }
            });
            geocoder.getAddress(latlng);
        });
        var latlon = position.coords.latitude+','+position.coords.longitude;

        //baidu
        var url = "http://api.map.baidu.com/geocoder/v2/?ak=C93b5178d7a8ebdb830b9b557abce78b&callback=renderReverse&location="+latlon+"&output=json&pois=0";
        $.ajax({
            type: "GET",
            dataType: "jsonp",
            url: url,
            beforeSend: function(){
                $("#baidu_geo").html('正在定位...');
            },
            success: function (json) {
                if(json.status==0){
                    $("#baidu_geo").html(json.result.formatted_address);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $("#baidu_geo").html(latlon+"地址位置获取失败");
            }
        });

        //google
        var url = 'http://maps.google.cn/maps/api/geocode/json?latlng='+latlon+'&language=CN';
        $.ajax({
            type: "GET",
            url: url,
            beforeSend: function(){
                $("#google_geo").html('正在定位...');
            },
            success: function (json) {
                if(json.status=='OK'){
                    var results = json.results;
                    $.each(results,function(index,array){
                        if(index==0){
                            $("#google_geo").html(array['formatted_address']);
                        }
                    });
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $("#google_geo").html(latlon+"地址位置获取失败");
            }
        });
    }

    function showError(error){
        switch(error.code) {
            case error.PERMISSION_DENIED:
                alert("定位失败,用户拒绝请求地理定位");
                break;
            case error.POSITION_UNAVAILABLE:
                alert("定位失败,位置信息是不可用");
                break;
            case error.TIMEOUT:
                alert("定位失败,请求获取用户位置超时");
                break;
            case error.UNKNOWN_ERROR:
                alert("定位失败,定位系统失效");
                break;
        }
    }

    getLocation();
</script>
<div id="footer">
    <p>Powered by helloweba.com  允许转载、修改和使用本站的DEMO，但请注明出处：<a href="http://www.helloweba.com">www.helloweba.com</a></p>
</div>

<p id="stat"><script type="text/javascript" src="/js/tongji.js"></script></p>
</body>
</html>
