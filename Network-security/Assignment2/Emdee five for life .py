import requests
import re
import hashlib

url = 'http://138.68.155.238:32060/'
r = requests.session()
html = r.get(url).text
output = re.findall(r'<h3 align=\'center\'>.*?</h3>', html)[0]
output = output.split('>')[1].split('<')[0]
final = hashlib.md5(output.encode(encoding='utf-8')).hexdigest()
data = {
    'hash': final
}
response = r.post(url=url, data=data).text
flag = re.findall(r'[a-zA-Z]+\{.*}', response)[0]
print(flag)
