package com.zxg.myokhttp.okutils;

/**
 * Author ：zxg on 2017/4/11 15:54
 * email : remotecountry@163.com
 */

public class CodeString {
    /**
     * 执行成功，没有错误
     */
    public static final String successCode = "0";
    public static final String successCode1 = "A02";//暂定，以后可能改
    public static final String successCodeString = "执行成功，没有错误";
    /**
     * 参数校验不通过
     */
    public static final String C01 = "C01";
    //    public static final String C01String = "参数校验不通过";
    public static final String C01String = "系统异常，请稍后再试";
    /**
     * 用户名已经存在，请使用其他用户名！
     */
    public static final String HBTS_U01 = "HBTS_U01";
    //    public static final String HBTS_U01String = "用户名已经存在，请使用其他用户名！";
    public static final String HBTS_U01String = "手机号码已存在，请重新输入";
    /**
     * 该手机号已经被使用，请更换手机号！
     */
    public static final String HBTS_U2 = "HBTS_U2";
    //    public static final String HBTS_U2String = "该手机号已经被使用，请更换手机号！";
    public static final String HBTS_U2String = "手机号码已存在，请重新输入";
    public static final String HBTS_U3 = "HBTS_U3";
    public static final String HBTS_U3String = "一个商户最多只能创建10个账号！";
    /**
     * 手机号不能为空
     */
    public static final String P01 = "P01";
    public static final String P01String = "手机号不能为空";
    /**
     * 手机号格式不正确
     */
    public static final String P02 = "P02";
    //    public static final String P02String = "手机号格式不正确";
    public static final String P02String = "请输入正确的手机号码";
    /**
     * 参数不能为null
     */
    public static final String P03 = "P03";
    //    public static final String P03String = "参数不能为null";
    public static final String P03String = "系统异常，请稍后再试";
    /**
     * 图片上传失败！
     */
    public static final String P04 = "P04";
    public static final String P04String = "图片上传失败！";
    /**
     * 系统错误
     */
    public static final String S01 = "S01";
    //    public static final String S01String = "系统错误";
    public static final String S01String = "系统异常，请稍后再试";
    /**
     * Token已过期！
     */
    public static final String T01 = "T01";
    //    public static final String T01String = "Token已过期！";
    public static final String T01String = "登录超时，请再次登录";
    /**
     * Token不存在！
     */
    public static final String T02 = "T02";
    //    public static final String T02String = "Token不存在！";
    public static final String T02String = "登录超时，请再次登录";
    /**
     * 缺少accessToken参数！
     */
    public static final String T03 = "T03";
    //    public static final String T03String = "缺少accessToken参数！";
    public static final String T03String = "系统异常，请稍后再试";
    /**
     * 用户不存在
     */
    public static final String U01 = "U01";
    //    public static final String U01String = "用户不存在";
    public static final String U01String = "该账户不存在";
    /**
     * 未找到手机号对应的商户
     */
    public static final String U04 = "U04";
    //    public static final String U04String = "未找到手机号对应的商户";
    public static final String U04String = "该账号不存在";
    /**
     * 验证码不匹配
     */
    public static final String U05 = "U05";
    //    public static final String U05String = "验证码不匹配";
    public static final String U05String = "验证码输入错误";
    /**
     * 原密码输入错误!
     */
    public static final String U08 = "U08";
    public static final String U08String = "原密码输入错误!";
    /**
     * 登录页面输入密码错误
     */
    public static final String U02 = "U02";
    //    public static final String U02String = "用户密码错误！";
    public static final String U02String = "密码错误请重新输入";
    public static final String U03 = "U03";
    //    public static final String U03String = "用户状态异常！";
    public static final String U03String = "商户账号异常，请联系客服";
    public static final String U06 = "U06";
    public static final String U06String = "只有老板账号才能删除子账号！";
    public static final String U07 = "U07";
    public static final String U07String = "不能删除自己!";
    public static final String U09 = "U09";
    public static final String U09String = "连续5次输入密码错误，请五分钟后重试";
    public static final String U10 = "U10";
    //    public static final String U10String = "该账户类型未开放手机app登陆";
    public static final String U10String = "该账号不存在";
    public static final String U11 = "U11";
    public static final String U11String = "连续5次输入验证码错误，请五分钟后重试";
    /**
     * 未绑卡信息
     */
    public static final String B410 = "410";
    public static final String B410String = "您还没有绑定任何银行卡";

    /**
     * 申请贷款出现的错误
     */
    public static final String CREDIT_001 = "CREDIT_001";
    public static final String CREDIT_001String = "贷款风控不通过";
    public static final String CREDIT_002 = "CREDIT_002";
    //    public static final String CREDIT_002String = "已存在审批中的申请";
    public static final String CREDIT_002String = "您有一笔正在审批中的贷款";
    public static final String CREDIT_003 = "CREDIT_003";
//    public static final String CREDIT_003String = "没有待结清还款计划";
    public static final String CREDIT_003String = "有一笔正在处理中的还款,请勿重复提交!";
    /**
     * 生成还款单号
     */
    public static final String CREDIT_005 = "CREDIT_005";
    //    public static final String CREDIT_005String = "花样年接口异常";
    public static final String CREDIT_005String = "系统异常，请稍后再试";
    public static final String CREDIT_004 = "CREDIT_004";
    //    public static final String CREDIT_004String = "已经还款,不能重新生成还款单号!";
    public static final String CREDIT_004String = "有一笔正在处理中的还款,请勿重复提交!";

    public static final String CREDIT_006 = "CREDIT_006";
    public static final String CREDIT_006String = "借款第二天才能还款";
    public static final String PAYPASS_005 = "PAYPASS_005";
    public static final String PAYPASS_005String = "当日输入次数超限，烦请次日重试";
    public static final String PAYPASS_002 = "PAYPASS_002";
    public static final String PAYPASS_002String = "传入信息有误";
    public static final String PAYPASS_003 = "PAYPASS_003";
    public static final String PAYPASS_003String = "支付密码错误";

    public static final String INFO_001 = "INFO_001";
    public static final String INFO_001String = "此卡尚未绑定";
    public static final String INFO_002 = "INFO_002";
    public static final String INFO_002String = "手机号输入错误";
    public static final String SG01 = "SG01";
    public static final String SG01String = "非法的签名";
    public static final String SG02 = "SG02";
//    public static final String SG02String = "过期的请求";
    public static final String SG02String = "请校验手机时间后，重启APP";
    public static final String SG03 = "SG03";
//    public static final String SG03String = "签名或加密数据格式错误";
    public static final String SG03String = "签名或加密数据格式错误,请重启APP";



}
