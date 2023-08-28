# Xades

This repository is Xades signature sign and verification sample code for Thailand etax invoice

## XadesBesSignByParametersMain : Parameters
<table>
  <tr>
    <td>input xml</td>
    <td>filepath</td>
  </tr>
  <tr>
    <td>output xml</td>
    <td>filepath</td>
  </tr>
  <tr>
    <td>method</td>
    <td>PKCS11</td>
  </tr>
  <tr>
    <td>driver</td>
    <td>/usr/lib/libeToken.so</td>
  </tr>
  <tr>
    <td>token label</td>
    <td>getting from: pkcs11-tool --module /usr/lib/libeToken.so --list-slots</td>
  </tr>
  <tr>
    <td>slot number</td>
    <td>getting from: pkcs11-tool --module /usr/lib/libeToken.so --list-slots</td>
  </tr>
  <tr>
    <td>certificate index</td>
    <td>getting from: pkcs11-tool --module /usr/lib/libeToken.so --list-objects --type cert --slot 0</td>
  </tr>
  <tr>
    <td>token password</td>
    <td></td>
  </tr>
</table>