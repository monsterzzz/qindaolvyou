public class T {
    public static void main(String[] args) {
        String s = "甘孜藏族自治州|0836|b62e5f99-2397-33b6-bb66-bc70fada65fe|MI 6 |Android 4.4.2|Xiaomi |720*1280|1|雅江县|17553423|1|635|29.99840060763889|100.56471299913194|四川省|29288|0|6.3.5rmrbsecurity$#%sut49fbb427a508bcc";
        //String a = MD5Helper.getMD5Str(s);

        String d = "2|%E7%94%98%E5%8D%97%E8%97%8F%E6%97%8F%E8%87%AA%E6%B2%BB%E5%B7%9E|0941|e35c7b71-8a73-30a3-83da-8a00e8a35526|HUAWEI%20MLA-AL10|Android%204.4.2|HUAWEI%20|720*1280|1|%E5%A4%8F%E6%B2%B3%E5%8E%BF|17503900|0|1280|720|635|35.00017469618056|102.56517550998264|1|%E7%94%98%E8%82%83%E7%9C%81|718966|0|20|0|6.3.5|";
        System.out.println(d);
        d = d.substring(0,d.length()-1);
        System.out.println(d);
        d = "2|甘孜藏族自治州|0836|b62e5f99-2397-33b6-bb66-bc70fada65fe|MI 6 |Android 4.4.2|Xiaomi |720*1280|1|雅江县|17553423|4260194|1280|720|635|29.99840060763889|100.56471299913194|3|四川省|4805720|1560290859|20|0|6.3.5rmrbsecurity$#%sut49fbb427a508bcc";
        String a = MD5Helper.getMD5Str(d);
        System.out.println(a);
    }
    
}
