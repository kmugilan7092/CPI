import com.sap.gateway.ip.core.customdev.util.Message;
def Message processData(Message message) {
    //Body
    def body = message.getBody(String) as String;
 def query = '<urn:sObjects xsi:type=' + '"' + 'urn1:Order_Document__c' + '">';
 def body1 = body.replaceAll("<sObjects>",query);
       def query1 = '</' + 'sObjects>';
       def query2 = '</urn:' + 'sObjects>';
       body1 = body1.replaceAll(query1,query2);
       message.setBody(body1);
       return message;
}