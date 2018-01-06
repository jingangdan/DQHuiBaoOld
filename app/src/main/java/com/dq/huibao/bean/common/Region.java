package com.dq.huibao.bean.common;

import java.util.List;

/**
 * 省市列表
 * Created by jingang on 2017/12/26.
 */

public class Region {

    /**
     * status : 1
     * data : [{"id":"2","region_name":"北京市","city":[{"id":"3","region_name":"北京市市辖区"}]},{"id":"20","region_name":"天津市","city":[{"id":"21","region_name":"天津市市辖区"}]},{"id":"38","region_name":"河北省","city":[{"id":"39","region_name":"石家庄市"},{"id":"62","region_name":"唐山市"},{"id":"77","region_name":"秦皇岛市"},{"id":"85","region_name":"邯郸市"},{"id":"104","region_name":"邢台市"},{"id":"124","region_name":"保定市"},{"id":"149","region_name":"张家口市"},{"id":"166","region_name":"承德市"},{"id":"178","region_name":"沧州市"},{"id":"195","region_name":"廊坊市"},{"id":"206","region_name":"衡水市"}]},{"id":"218","region_name":"山西省","city":[{"id":"219","region_name":"太原市"},{"id":"230","region_name":"大同市"},{"id":"242","region_name":"阳泉市"},{"id":"248","region_name":"长治市"},{"id":"262","region_name":"晋城市"},{"id":"269","region_name":"朔州市"},{"id":"276","region_name":"晋中市"},{"id":"288","region_name":"运城市"},{"id":"302","region_name":"忻州市"},{"id":"317","region_name":"临汾市"},{"id":"335","region_name":"吕梁市"}]},{"id":"349","region_name":"内蒙古自治区","city":[{"id":"350","region_name":"呼和浩特市"},{"id":"360","region_name":"包头市"},{"id":"370","region_name":"乌海市"},{"id":"374","region_name":"赤峰市"},{"id":"387","region_name":"通辽市"},{"id":"396","region_name":"鄂尔多斯市"},{"id":"406","region_name":"呼伦贝尔市"},{"id":"421","region_name":"巴彦淖尔市"},{"id":"429","region_name":"乌兰察布市"},{"id":"441","region_name":"兴安盟"},{"id":"448","region_name":"锡林郭勒盟"},{"id":"461","region_name":"阿拉善盟"}]},{"id":"465","region_name":"辽宁省","city":[{"id":"466","region_name":"沈阳市"},{"id":"480","region_name":"大连市"},{"id":"491","region_name":"鞍山市"},{"id":"499","region_name":"抚顺市"},{"id":"507","region_name":"本溪市"},{"id":"514","region_name":"丹东市"},{"id":"521","region_name":"锦州市"},{"id":"529","region_name":"营口市"},{"id":"536","region_name":"阜新市"},{"id":"544","region_name":"辽阳市"},{"id":"552","region_name":"盘锦市"},{"id":"557","region_name":"铁岭市"},{"id":"565","region_name":"朝阳市"},{"id":"573","region_name":"葫芦岛市"}]},{"id":"580","region_name":"吉林省","city":[{"id":"581","region_name":"长春市"},{"id":"592","region_name":"吉林市"},{"id":"602","region_name":"四平市"},{"id":"609","region_name":"辽源市"},{"id":"614","region_name":"通化市"},{"id":"622","region_name":"白山市"},{"id":"629","region_name":"松原市"},{"id":"635","region_name":"白城市"},{"id":"641","region_name":"延边朝鲜族自治州"}]},{"id":"650","region_name":"黑龙江省","city":[{"id":"651","region_name":"哈尔滨市"},{"id":"670","region_name":"齐齐哈尔市"},{"id":"687","region_name":"鸡西市"},{"id":"697","region_name":"鹤岗市"},{"id":"706","region_name":"双鸭山市"},{"id":"715","region_name":"大庆市"},{"id":"725","region_name":"伊春市"},{"id":"743","region_name":"佳木斯市"},{"id":"754","region_name":"七台河市"},{"id":"759","region_name":"牡丹江市"},{"id":"770","region_name":"黑河市"},{"id":"777","region_name":"绥化市"},{"id":"788","region_name":"大兴安岭地区"}]},{"id":"793","region_name":"上海市","city":[{"id":"794","region_name":"上海市市辖区"}]},{"id":"811","region_name":"江苏省","city":[{"id":"812","region_name":"南京市"},{"id":"824","region_name":"无锡市"},{"id":"832","region_name":"徐州市"},{"id":"843","region_name":"常州市"},{"id":"850","region_name":"苏州市"},{"id":"860","region_name":"南通市"},{"id":"869","region_name":"连云港市"},{"id":"876","region_name":"淮安市"},{"id":"884","region_name":"盐城市"},{"id":"894","region_name":"扬州市"},{"id":"901","region_name":"镇江市"},{"id":"908","region_name":"泰州市"},{"id":"915","region_name":"宿迁市"}]},{"id":"921","region_name":"浙江省","city":[{"id":"922","region_name":"杭州市"},{"id":"936","region_name":"宁波市"},{"id":"947","region_name":"温州市"},{"id":"959","region_name":"嘉兴市"},{"id":"967","region_name":"湖州市"},{"id":"973","region_name":"绍兴市"},{"id":"980","region_name":"金华市"},{"id":"990","region_name":"衢州市"},{"id":"997","region_name":"舟山市"},{"id":"1002","region_name":"台州市"},{"id":"1012","region_name":"丽水市"}]},{"id":"1022","region_name":"安徽省","city":[{"id":"1023","region_name":"合肥市"},{"id":"1033","region_name":"芜湖市"},{"id":"1042","region_name":"蚌埠市"},{"id":"1050","region_name":"淮南市"},{"id":"1058","region_name":"马鞍山市"},{"id":"1065","region_name":"淮北市"},{"id":"1070","region_name":"铜陵市"},{"id":"1075","region_name":"安庆市"},{"id":"1086","region_name":"黄山市"},{"id":"1094","region_name":"滁州市"},{"id":"1103","region_name":"阜阳市"},{"id":"1112","region_name":"宿州市"},{"id":"1118","region_name":"六安市"},{"id":"1126","region_name":"亳州市"},{"id":"1131","region_name":"池州市"},{"id":"1136","region_name":"宣城市"}]},{"id":"1144","region_name":"福建省","city":[{"id":"1145","region_name":"福州市"},{"id":"1159","region_name":"厦门市"},{"id":"1166","region_name":"莆田市"},{"id":"1172","region_name":"三明市"},{"id":"1185","region_name":"泉州市"},{"id":"1198","region_name":"漳州市"},{"id":"1210","region_name":"南平市"},{"id":"1221","region_name":"龙岩市"},{"id":"1229","region_name":"宁德市"}]},{"id":"1239","region_name":"江西省","city":[{"id":"1240","region_name":"南昌市"},{"id":"1250","region_name":"景德镇市"},{"id":"1255","region_name":"萍乡市"},{"id":"1261","region_name":"九江市"},{"id":"1275","region_name":"新余市"},{"id":"1278","region_name":"鹰潭市"},{"id":"1282","region_name":"赣州市"},{"id":"1301","region_name":"吉安市"},{"id":"1315","region_name":"宜春市"},{"id":"1326","region_name":"抚州市"},{"id":"1338","region_name":"上饶市"}]},{"id":"1351","region_name":"山东省","city":[{"id":"1352","region_name":"济南市"},{"id":"1363","region_name":"青岛市"},{"id":"1374","region_name":"淄博市"},{"id":"1383","region_name":"枣庄市"},{"id":"1390","region_name":"东营市"},{"id":"1396","region_name":"烟台市"},{"id":"1409","region_name":"潍坊市"},{"id":"1422","region_name":"济宁市"},{"id":"1434","region_name":"泰安市"},{"id":"1441","region_name":"威海市"},{"id":"1446","region_name":"日照市"},{"id":"1451","region_name":"莱芜市"},{"id":"1454","region_name":"临沂市"},{"id":"1467","region_name":"德州市"},{"id":"1479","region_name":"聊城市"},{"id":"1488","region_name":"滨州市"},{"id":"1496","region_name":"菏泽市"}]},{"id":"1506","region_name":"河南省","city":[{"id":"1507","region_name":"郑州市"},{"id":"1520","region_name":"开封市"},{"id":"1530","region_name":"洛阳市"},{"id":"1546","region_name":"平顶山市"},{"id":"1557","region_name":"安阳市"},{"id":"1567","region_name":"鹤壁市"},{"id":"1573","region_name":"新乡市"},{"id":"1586","region_name":"焦作市"},{"id":"1597","region_name":"濮阳市"},{"id":"1604","region_name":"许昌市"},{"id":"1611","region_name":"漯河市"},{"id":"1617","region_name":"三门峡市"},{"id":"1624","region_name":"南阳市"},{"id":"1638","region_name":"商丘市"},{"id":"1648","region_name":"信阳市"},{"id":"1659","region_name":"周口市"},{"id":"1670","region_name":"驻马店市"},{"id":"1681","region_name":"济源市"}]},{"id":"1682","region_name":"湖北省","city":[{"id":"1683","region_name":"武汉市"},{"id":"1697","region_name":"黄石市"},{"id":"1704","region_name":"十堰市"},{"id":"1713","region_name":"宜昌市"},{"id":"1727","region_name":"襄阳市"},{"id":"1737","region_name":"鄂州市"},{"id":"1741","region_name":"荆门市"},{"id":"1747","region_name":"孝感市"},{"id":"1755","region_name":"荆州市"},{"id":"1764","region_name":"黄冈市"},{"id":"1775","region_name":"咸宁市"},{"id":"1782","region_name":"随州市"},{"id":"1786","region_name":"恩施土家族苗族自治州"},{"id":"1795","region_name":"仙桃市"},{"id":"1796","region_name":"潜江市"},{"id":"1797","region_name":"天门市"},{"id":"1798","region_name":"神农架林区"}]},{"id":"1799","region_name":"湖南省","city":[{"id":"1800","region_name":"长沙市"},{"id":"1810","region_name":"株洲市"},{"id":"1820","region_name":"湘潭市"},{"id":"1826","region_name":"衡阳市"},{"id":"1839","region_name":"邵阳市"},{"id":"1852","region_name":"岳阳市"},{"id":"1862","region_name":"常德市"},{"id":"1872","region_name":"张家界市"},{"id":"1877","region_name":"益阳市"},{"id":"1884","region_name":"郴州市"},{"id":"1896","region_name":"永州市"},{"id":"1908","region_name":"怀化市"},{"id":"1921","region_name":"娄底市"},{"id":"1927","region_name":"湘西土家族苗族自治州"}]},{"id":"1936","region_name":"广东省","city":[{"id":"1937","region_name":"广州市"},{"id":"1949","region_name":"韶关市"},{"id":"1960","region_name":"深圳市"},{"id":"1967","region_name":"珠海市"},{"id":"1971","region_name":"汕头市"},{"id":"1979","region_name":"佛山市"},{"id":"1985","region_name":"江门市"},{"id":"1993","region_name":"湛江市"},{"id":"2003","region_name":"茂名市"},{"id":"2009","region_name":"肇庆市"},{"id":"2018","region_name":"惠州市"},{"id":"2024","region_name":"梅州市"},{"id":"2033","region_name":"汕尾市"},{"id":"2038","region_name":"河源市"},{"id":"2045","region_name":"阳江市"},{"id":"2050","region_name":"清远市"},{"id":"2059","region_name":"东莞市"},{"id":"2063","region_name":"中山市"},{"id":"2066","region_name":"潮州市"},{"id":"2070","region_name":"揭阳市"},{"id":"2076","region_name":"云浮市"}]},{"id":"2082","region_name":"广西壮族自治区","city":[{"id":"2083","region_name":"南宁市"},{"id":"2096","region_name":"柳州市"},{"id":"2107","region_name":"桂林市"},{"id":"2125","region_name":"梧州市"},{"id":"2133","region_name":"北海市"},{"id":"2138","region_name":"防城港市"},{"id":"2143","region_name":"钦州市"},{"id":"2148","region_name":"贵港市"},{"id":"2154","region_name":"玉林市"},{"id":"2162","region_name":"百色市"},{"id":"2175","region_name":"贺州市"},{"id":"2181","region_name":"河池市"},{"id":"2193","region_name":"来宾市"},{"id":"2200","region_name":"崇左市"}]},{"id":"2208","region_name":"海南省","city":[{"id":"2209","region_name":"海口市"},{"id":"2214","region_name":"三亚市"},{"id":"2226","region_name":"三沙市"},{"id":"2230","region_name":"儋州市"},{"id":"2231","region_name":"五指山市"},{"id":"2232","region_name":"琼海市"},{"id":"2233","region_name":"文昌市"},{"id":"2234","region_name":"万宁市"},{"id":"2235","region_name":"东方市"},{"id":"2236","region_name":"定安县"},{"id":"2237","region_name":"屯昌县"},{"id":"2238","region_name":"澄迈县"},{"id":"2239","region_name":"临高县"},{"id":"2240","region_name":"白沙黎族自治县"},{"id":"2241","region_name":"昌江黎族自治县"},{"id":"2242","region_name":"乐东黎族自治县"},{"id":"2243","region_name":"陵水黎族自治县"},{"id":"2244","region_name":"保亭黎族苗族自治县"},{"id":"2245","region_name":"琼中黎族苗族自治县"}]},{"id":"2246","region_name":"重庆市","city":[{"id":"2247","region_name":"重庆市市辖区"},{"id":"2286","region_name":"重庆市郊县"}]},{"id":"2287","region_name":"四川省","city":[{"id":"2288","region_name":"成都市"},{"id":"2309","region_name":"自贡市"},{"id":"2316","region_name":"攀枝花市"},{"id":"2322","region_name":"泸州市"},{"id":"2330","region_name":"德阳市"},{"id":"2337","region_name":"绵阳市"},{"id":"2347","region_name":"广元市"},{"id":"2355","region_name":"遂宁市"},{"id":"2361","region_name":"内江市"},{"id":"2367","region_name":"乐山市"},{"id":"2379","region_name":"南充市"},{"id":"2389","region_name":"眉山市"},{"id":"2396","region_name":"宜宾市"},{"id":"2407","region_name":"广安市"},{"id":"2414","region_name":"达州市"},{"id":"2422","region_name":"雅安市"},{"id":"2431","region_name":"巴中市"},{"id":"2437","region_name":"资阳市"},{"id":"2441","region_name":"阿坝藏族羌族自治州"},{"id":"2455","region_name":"甘孜藏族自治州"},{"id":"2474","region_name":"凉山彝族自治州"}]},{"id":"2492","region_name":"贵州省","city":[{"id":"2493","region_name":"贵阳市"},{"id":"2504","region_name":"六盘水市"},{"id":"2509","region_name":"遵义市"},{"id":"2524","region_name":"安顺市"},{"id":"2531","region_name":"毕节市"},{"id":"2540","region_name":"铜仁市"},{"id":"2551","region_name":"黔西南布依族苗族自治州"},{"id":"2560","region_name":"黔东南苗族侗族自治州"},{"id":"2577","region_name":"黔南布依族苗族自治州"}]},{"id":"2590","region_name":"云南省","city":[{"id":"2591","region_name":"昆明市"},{"id":"2606","region_name":"曲靖市"},{"id":"2616","region_name":"玉溪市"},{"id":"2626","region_name":"保山市"},{"id":"2632","region_name":"昭通市"},{"id":"2644","region_name":"丽江市"},{"id":"2650","region_name":"普洱市"},{"id":"2661","region_name":"临沧市"},{"id":"2670","region_name":"楚雄彝族自治州"},{"id":"2681","region_name":"红河哈尼族彝族自治州"},{"id":"2695","region_name":"文山壮族苗族自治州"},{"id":"2704","region_name":"西双版纳傣族自治州"},{"id":"2708","region_name":"大理白族自治州"},{"id":"2721","region_name":"德宏傣族景颇族自治州"},{"id":"2727","region_name":"怒江傈僳族自治州"},{"id":"2732","region_name":"迪庆藏族自治州"}]},{"id":"2736","region_name":"西藏自治区","city":[{"id":"2737","region_name":"拉萨市"},{"id":"2746","region_name":"日喀则市"},{"id":"2765","region_name":"昌都市"},{"id":"2777","region_name":"山南市"},{"id":"2790","region_name":"那曲地区"},{"id":"2802","region_name":"阿里地区"},{"id":"2810","region_name":"林芝市"}]},{"id":"2818","region_name":"陕西省","city":[{"id":"2819","region_name":"西安市"},{"id":"2833","region_name":"铜川市"},{"id":"2838","region_name":"宝鸡市"},{"id":"2851","region_name":"咸阳市"},{"id":"2866","region_name":"渭南市"},{"id":"2878","region_name":"延安市"},{"id":"2892","region_name":"汉中市"},{"id":"2904","region_name":"榆林市"},{"id":"2917","region_name":"安康市"},{"id":"2928","region_name":"商洛市"}]},{"id":"2936","region_name":"甘肃省","city":[{"id":"2937","region_name":"兰州市"},{"id":"2946","region_name":"嘉峪关市"},{"id":"2947","region_name":"金昌市"},{"id":"2950","region_name":"白银市"},{"id":"2956","region_name":"天水市"},{"id":"2964","region_name":"武威市"},{"id":"2969","region_name":"张掖市"},{"id":"2976","region_name":"平凉市"},{"id":"2984","region_name":"酒泉市"},{"id":"2992","region_name":"庆阳市"},{"id":"3001","region_name":"定西市"},{"id":"3009","region_name":"陇南市"},{"id":"3019","region_name":"临夏回族自治州"},{"id":"3028","region_name":"甘南藏族自治州"}]},{"id":"3037","region_name":"青海省","city":[{"id":"3038","region_name":"西宁市"},{"id":"3046","region_name":"海东市"},{"id":"3053","region_name":"海北藏族自治州"},{"id":"3058","region_name":"黄南藏族自治州"},{"id":"3063","region_name":"海南藏族自治州"},{"id":"3069","region_name":"果洛藏族自治州"},{"id":"3076","region_name":"玉树藏族自治州"},{"id":"3083","region_name":"海西蒙古族藏族自治州"}]},{"id":"3090","region_name":"宁夏回族自治区","city":[{"id":"3091","region_name":"银川市"},{"id":"3098","region_name":"石嘴山市"},{"id":"3102","region_name":"吴忠市"},{"id":"3108","region_name":"固原市"},{"id":"3114","region_name":"中卫市"}]},{"id":"3118","region_name":"新疆维吾尔自治区","city":[{"id":"3119","region_name":"乌鲁木齐市"},{"id":"3128","region_name":"克拉玛依市"},{"id":"3133","region_name":"吐鲁番市"},{"id":"3137","region_name":"哈密市"},{"id":"3141","region_name":"昌吉回族自治州"},{"id":"3149","region_name":"博尔塔拉蒙古自治州"},{"id":"3154","region_name":"巴音郭楞蒙古自治州"},{"id":"3164","region_name":"阿克苏地区"},{"id":"3174","region_name":"克孜勒苏柯尔克孜自治州"},{"id":"3179","region_name":"喀什地区"},{"id":"3192","region_name":"和田地区"},{"id":"3201","region_name":"伊犁哈萨克自治州"},{"id":"3213","region_name":"塔城地区"},{"id":"3221","region_name":"阿勒泰地区"},{"id":"3229","region_name":"石河子市"},{"id":"3230","region_name":"阿拉尔市"},{"id":"3231","region_name":"图木舒克市"},{"id":"3232","region_name":"五家渠市"},{"id":"3233","region_name":"北屯市"},{"id":"3234","region_name":"铁门关市"},{"id":"3235","region_name":"双河市"},{"id":"3236","region_name":"可克达拉市"},{"id":"3237","region_name":"昆玉市"}]},{"id":"3238","region_name":"台湾省","city":[]},{"id":"3239","region_name":"香港特别行政区","city":[{"id":"3240","region_name":"中西区"},{"id":"3241","region_name":"湾仔区"},{"id":"3242","region_name":"东区"},{"id":"3243","region_name":"南区"},{"id":"3244","region_name":"油尖旺区"},{"id":"3245","region_name":"深水埗区"},{"id":"3246","region_name":"九龙城区"},{"id":"3247","region_name":"黄大仙区"},{"id":"3248","region_name":"观塘区"},{"id":"3249","region_name":"荃湾区"},{"id":"3250","region_name":"屯门区"},{"id":"3251","region_name":"元朗区"},{"id":"3252","region_name":"北区"},{"id":"3253","region_name":"大埔区"},{"id":"3254","region_name":"西贡区"},{"id":"3255","region_name":"沙田区"},{"id":"3256","region_name":"葵青区"},{"id":"3257","region_name":"离岛区"}]},{"id":"3258","region_name":"澳门特别行政区","city":[{"id":"3259","region_name":"花地玛堂区"},{"id":"3260","region_name":"花王堂区"},{"id":"3261","region_name":"望德堂区"},{"id":"3262","region_name":"大堂区"},{"id":"3263","region_name":"风顺堂区"},{"id":"3264","region_name":"嘉模堂区"},{"id":"3265","region_name":"路凼填海区"},{"id":"3266","region_name":"圣方济各堂区"}]}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * region_name : 北京市
         * city : [{"id":"3","region_name":"北京市市辖区"}]
         */

        private String id;
        private String region_name;
        private List<CityBean> city;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public static class CityBean {
            /**
             * id : 3
             * region_name : 北京市市辖区
             */

            private String id;
            private String region_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            @Override
            public String toString() {
                return "CityBean{" +
                        "id='" + id + '\'' +
                        ", region_name='" + region_name + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", region_name='" + region_name + '\'' +
                    ", city=" + city +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Region{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
