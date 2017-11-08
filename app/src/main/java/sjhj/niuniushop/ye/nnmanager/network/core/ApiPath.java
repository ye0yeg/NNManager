package sjhj.niuniushop.ye.nnmanager.network.core;


public interface ApiPath {

    String COLLECT_DELETE = "/user/collect/delete";
    String COLLECT_LIST = "/user/collect/list";
    String COLLECT_CREATE = "/user/collect/create";
    String ORDER_CANCEL = "/order/cancel";
    String ORDER_LIST = "/order/list";
    String ORDER_DONE = "/flow/done";
    String ORDER_PREVIEW = "/flow/checkOrder";
    String ADDRESS_INFO = "/address/info";
    String ADDRESS_UPDATE = "/address/update";
    String ADDRESS_DELETE = "/address/delete";
    String ADDRESS_DEFAULT = "/address/setDefault";
    String ADDRESS_ADD = "/address/add";
    String ADDRESS_LIST = "/address/list";
    String CART_CREATE = "/cart/create";
    String CART_DELETE = "/cart/delete";
    String CART_LIST = "/cart/list";
    String CART_UPDATE = "/cart/update";
    String CATEGORY = "/category";
    String GOODS = "/goods";
    String HOME_DATA = "/home/data";
    String HOME_CATEGORY = "/home/category";
    String REGION = "/region";
    String SEARCH = "/search";
    String USER_SIGNIN = "/user/signin";
    String USER_SIGNUP = "/user/signup";
    String USER_INFO = "/user/info";


//    加入一些My own API PATH

    String SJHJ_BASE_URL = "http://wx.nn-mall.com/api/test.php?event=";
    String SJHJ_BASE_PIC_URL ="http://wx.nn-mall.com/attachment/";
    String SJHJ_CATEGORY = "type";
    String SJHJ_CATEGORY_DETAIL = "&ccate=";
    String SJHJ_CATEGORY_SHOPLIST = "shoplist";
    String SJHJ_CATEGORY_ORDER ="&orderby=";
    String SJHJ_CATEGORY_SEARCH ="&keyword=";

    String SJHJ_SHOPINFO = "shopinfo";
    String SJHJ_GOODS_ID = "&id=";

    //http://wx.nn-mall.com/api/test.php?event=shopinfo&id=123
}
