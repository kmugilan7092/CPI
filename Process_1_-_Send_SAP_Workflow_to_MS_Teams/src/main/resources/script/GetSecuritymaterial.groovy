/* ============================================================================
 *  Script Name    : GetSecuritymaterial
 *  Author         : MUGILAN KANAGARAJ - SAP Technical & Integration Consultant
 *  Script Type    : Groovy Script
 *  Project        : SAP Inbox Workflow Approval Integration with Microsoft Standard Approvals
 *  GuidedBy       : Arpith Bhat | Mohammed Aarish
 *  Product Vendor : KaarTech
 *  Website        : https://www.kaartech.com
 * ============================================================================
 *  Description    :
 *      Retrieves Client ID and Client Secret from SAP CPI Security Material
 *      using the configured credential alias and stores them in exchange
 *      properties (CPIClientUser and CPIClientSecret) for authentication
 *      in subsequent integration flow steps.
 *
 * ============================================================================
 */
 
import com.sap.gateway.ip.core.customdev.util.Message
import com.sap.it.api.ITApiFactory
import com.sap.it.api.securestore.SecureStoreService

def Message processData(Message message) {
    // Alias name comes from the exchange property
    String credName = message.getProperty("SecurityMaterial")

    if (!credName) {
        throw new Exception("Exchange property 'SecurityMaterial' is empty or missing")
    }

    def secureStoreService = ITApiFactory.getApi(SecureStoreService.class, null)
    def credential = secureStoreService.getUserCredential(credName)

    if (credential == null) {
        throw new Exception("Credential alias '" + credName + "' not found in Monitoring >> Security Material")
    }

    message.setProperty("CPIClientUser", credential.getUsername())
    message.setProperty("CPIClientSecret", new String(credential.getPassword()))

    return message
}