//
///**
// * @Author 作者：马健
// * @Phone  联系qq：1039510196
// * @CreateTime 创建时间：2017年6月14日 下午3:34:28
// * @Details
// */
//package wtb.smUtil;
//
///**
// * @author Administrator
// *
// */
//public class EmailContentModel {
//
//	public static String Html(String name,String message){
//		String html="";
//			html+="<div id=mainmail style=margin-bottom: 12px; position: relative; z-index: 1;>";
//			html+="<div class=body id=contentDiv style=padding: 15px 15px 10px; height: auto; line-height: 1.7; font-size: 14px; position: relative; z-index: 1; -ms-zoom: 1;>";
//			html+="<div id=qm_con_body>";
//			html+="<div class=emailbox style=height: auto;min-height: 100px;word-wrap: break-word;font-size: 14px;font-family: lucida Grande, Verdana, Microsoft YaHei; id=mailContentContainer> <br><br>";
//			html+="<table align=center border=0 cellspacing=0 cellpadding=0>";
//			html+="<tr>";
//			html+="<td align=left valign=top>";
//			html+="<table width=500 border=0 cellspacing=0 cellpadding=0>";
//			html+="<tr>";
//			html+="<td align=right valign=top text-align=center></td>";
//			html+=name+"<td align=right valign=top></td>";
//			html+="<td align=right valign=top>&nbsp;</td>";
//			html+="</tr>";
//			html+="<tr>";
//			html+="<td colspan=2><img width=500 height=1 src=cid:email_line border=0></td>";
//			html+="</tr>";
//			html+="<tr height=24>";
//			html+="<td height=24 colspan=2>";
//			html+="</tr>";
//			html+="<tr>";
//			html+="<td style=color: rgb(51, 51, 51); font-family: Geneva,Verdana,Arial,Helvetica; font-size: 10px; font-weight: normal; colspan=2>";
//			html+=message;
//			html+="</td>";
//			html+="</tr>";
//			html+="<tr height=24>";
//			html+="<td height=24 colspan=2>";
//			html+="</tr>";
//			html+="<tr>";
//			html+="<td colspan=2><img width=500 height=1 src=cid:email_line border=0></td>";
//			html+="</tr>";
//			html+="</table>";
//			html+="</td>";
//			html+="</tr>";
//			html+="<tr height=20>";
//			html+="<td height=20>";
//			html+="</tr>";
//			html+="<tr>";
//			html+="<td>";
//			html+="<table align=center>";
//			html+="<tr>";
//			html+="<td width=500 align=center class=message style=color: rgb(51, 51, 51); font-family: Geneva,Verdana,Arial,Helvetica; font-size: 9px;><img  src=http://wenews.top:80/WeNewsAgency/images/wenewspc/logo.png border=0><br/> <br>该邮件为系统自动发送，请勿直接回复<br>";
//			html+="<br></td>";
//			html+="</tr>";
//			html+="<tr>";
//			html+="<td width=500 align=center class=disclaimer style=color: rgb(110, 110, 110); font-family: Geneva,Verdana,Arial,Helvetica; font-size: 9px;> Copyright @ 2017 校播科技";
//			html+="<a href=http://wenews.top/WeNewsAgency/WeNewsHome target=_blank>保留所有权利</a>";
//			html+="</td>";
//			html+="</tr>";
//			html+="</table>";
//			html+="</td>";
//			html+="</tr>";
//			html+="</table><br><br> ";
//			html+="</div>";
//			html+="</div>";
//			html+="</div>";
//		return html;
//	}
//	
//}
