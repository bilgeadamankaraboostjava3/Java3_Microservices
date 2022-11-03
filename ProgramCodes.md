     X X X X
     | | | |
     | | | --- 1  -> İşlem Kodu
     | | ----- 2  -> İşlem Kodu
     | ------- 3 -> 2 -> Success
                    4 -> Error
                    5 -> Server Error
     --------- 4 -> 1. Auth Serice
                    2. Main Service
                    3. User Service
                    
# AUTH SERVICES
## 12XX: Başarılı işlemler
    1200: Kayıt Başarılı
    1201: Giriş Başarılı
## 14XX: Hatalı işlemler
    1400: Kayıt Başarısız
    1401: Giriş Başarısız
    1402: Kullanıcı Adı veya Şifre Hatalı
    1403: Kullanıcı zaten kayıtlı
## 15XX: Sunucu Hataları
    1500: Kayıt Sunucu Hatası
    1501: Giriş Sunucu Hatası




# MAIN SERVICES
## 2000:




# USER SERVICES
## 3000: 