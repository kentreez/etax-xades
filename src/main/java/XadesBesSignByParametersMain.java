public class XadesBesSignByParametersMain {

	private static String xmlInput;
	private static String xmlOutput;
	private static String pkType;
	private static String pkcs11LibPath;
	private static String pkcs11ProviderName;
	private static int pkcs11SlotId;
    private static int pkcs11CertificateIndex;
	private static String pkcs11Password;
	private static String pkcs12Path;
	private static String pkcs12Password;

	public static void main(String[] args) {

		// BasicConfigurator.configure();

		XadesBesSigner signer = new XadesBesSigner();

		try {
            xmlInput = args[0];
            xmlOutput = args[1];
            pkType = args[2];

			if (pkType.equals("PKCS11")) {
                pkcs11LibPath = args[3];
                pkcs11ProviderName = args[4];
                pkcs11SlotId = Integer.parseInt(args[5]);
                pkcs11CertificateIndex = Integer.parseInt(args[6]);
                pkcs11Password = args[7];
				// P11 signer
				signer.setSignerPkcs11(pkcs11LibPath, pkcs11ProviderName, pkcs11SlotId, pkcs11CertificateIndex, pkcs11Password);
			} else if (pkType.equals("PKCS12")) {
				// P12 signer
                pkcs12Path = args[3];
                pkcs12Password = args[4];
				signer.setSignerPkcs12(pkcs12Path, pkcs12Password, pkType);
			} else {
				throw new Exception("PK_TYPE_not_supported");
			}

			System.out.println("==============\tSign\t==============");
			signer.signWithoutIDEnveloped(xmlInput, xmlOutput);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("==============\tFinish\t==============");
        System.exit(0);
	}
}
