
var isReady=false;var onReadyCallbacks=[];
var isServiceReady=false;var onServiceReadyCallbacks=[];
var __uniConfig = {"pages":["pages/login/login","pages/my/my","pages/index/index","pages/dynamic/dynamic","pages/msg/msg","pages/settings/settings","pages/upload/upload","pages/user-safe/user-safe","pages/edit-password/edit-password","pages/about/about","pages/user-feedback/user-feedback"],"window":{"navigationBarTextStyle":"white","navigationBarTitleText":"我的应用","navigationBarBackgroundColor":"#FC5C82","backgroundColor":"#F8F8F8","pageOrientation":"portrait","rpxCalcMaxDeviceWidth":960,"rpxCalcBaseDeviceWidth":375,"rpxCalcIncludeWidth":750},"tabBar":{"color":"#333333","selectedColor":"#FC5C82","backgroundColor":"#FFFFFF","list":[{"pagePath":"pages/index/index","text":"首页","iconPath":"static/tabbar/index.png","selectedIconPath":"static/tabbar/indexed.png"},{"pagePath":"pages/dynamic/dynamic","text":"动态","iconPath":"static/tabbar/news.png","selectedIconPath":"static/tabbar/newsed.png"},{"pagePath":"pages/msg/msg","text":"消息","iconPath":"static/tabbar/paper.png","selectedIconPath":"static/tabbar/papered.png"},{"pagePath":"pages/my/my","text":"我的","iconPath":"static/tabbar/home.png","selectedIconPath":"static/tabbar/homeed.png"}]},"nvueCompiler":"uni-app","nvueStyleCompiler":"uni-app","renderer":"auto","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"uni-news","compilerVersion":"3.3.13","entryPagePath":"pages/login/login","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/login/login","meta":{"isQuit":true},"window":{"navigationStyle":"custom","navigationBarTextStyle":"black"}},{"path":"/pages/my/my","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"我的","enablePullDownRefresh":false,"titleNView":{"buttons":[{"type":"menu","color":"#FFFFFF"}]}}},{"path":"/pages/index/index","meta":{"isQuit":true,"isTabBar":true},"window":{"enablePullDownRefresh":true,"titleNView":{"searchInput":{"placeholder":"search","placeholderColor":"#6f6c67","backgroundColor":"#F5F4F2","align":"center","borderRadius":"4px"},"buttons":[{"color":"#FFFFFF","colorPressed":"#FC5C82","float":"right","fontSize":"20px","fontSrc":"/static/font/iconfont.ttf","text":""}]}}},{"path":"/pages/dynamic/dynamic","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationStyle":"custom","navigationBarTextStyle":"black"}},{"path":"/pages/msg/msg","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"消息","enablePullDownRefresh":true,"titleNView":{"buttons":[{"color":"#FFFFFF","colorPressed":"#FC5C82","float":"left","fontSize":"20px","fontSrc":"/static/font/iconfont.ttf","text":""},{"color":"#FFFFFF","colorPressed":"#FC5C82","float":"right","fontSize":"20px","fontSrc":"/static/font/iconfont.ttf","text":""}]}}},{"path":"/pages/settings/settings","meta":{},"window":{"navigationBarTitleText":"","enablePullDownRefresh":false}},{"path":"/pages/upload/upload","meta":{},"window":{"navigationBarTitleText":"编辑资料","enablePullDownRefresh":false}},{"path":"/pages/user-safe/user-safe","meta":{},"window":{"navigationBarTitleText":"账号安全","enablePullDownRefresh":false}},{"path":"/pages/edit-password/edit-password","meta":{},"window":{"navigationBarTitleText":"修改密码","enablePullDownRefresh":false}},{"path":"/pages/about/about","meta":{},"window":{"navigationBarTitleText":"关于社区","enablePullDownRefresh":false}},{"path":"/pages/user-feedback/user-feedback","meta":{},"window":{"navigationBarTitleText":"意见反馈","enablePullDownRefresh":false}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
__uniConfig.onServiceReady=function(callback){if(__uniConfig.serviceReady){callback()}else{onServiceReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"serviceReady",{get:function(){return isServiceReady},set:function(val){isServiceReady=val;if(!isServiceReady){return}const callbacks=onServiceReadyCallbacks.slice(0);onServiceReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:void 0,window:void 0,document:void 0,frames:void 0,self:void 0,location:void 0,navigator:void 0,localStorage:void 0,history:void 0,Caches:void 0,screen:void 0,alert:void 0,confirm:void 0,prompt:void 0,fetch:void 0,XMLHttpRequest:void 0,WebSocket:void 0,webkit:void 0,print:void 0}}}});
