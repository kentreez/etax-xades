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
    <td>certificate serial</td>
    <td>run below shell script to get list of serial</td>
  </tr>
  <tr>
    <td>token password</td>
    <td></td>
  </tr>
</table>

```shell
#!/bin/sh

# Check if $1 is set
if [ -z "$1" ]; then
    echo "Error: Slot is not set."
    echo "Usage: $0 <slot>"
    exit 1  # Exit with a non-zero status code indicating an error
fi

# Check if $1 is an integer
if ! [ "$1" -eq "$1" ] 2>/dev/null; then
    echo "Error: Slot is not an integer."
    exit 1
fi

lines=$( pkcs11-tool --module /usr/lib/libeToken.so --list-objects --type cert --slot $1 | awk '/label:/ || /subject:/ {if (/label:/) {printf "%s!",$2} else {gsub(/.*CN=/, ""); print}}' )

echo "$lines" | while IFS= read -r line; do
  label=$(echo "$line" | cut -d'!' -f1)
  CN=$(echo "$line" | cut -d'!' -f2)
  echo $CN | sed 's/\\x//g' | xxd -r -p
  echo ''
  pkcs11-tool --module /usr/lib/libeToken.so --slot 0 --read-object --type cert --label ${label} | openssl x509 -inform DER -noout -serial -enddate
  echo '---------------------------------'
done
```