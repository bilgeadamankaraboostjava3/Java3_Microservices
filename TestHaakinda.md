# Test Yazımı Hakkında Genel Bilgiler

## 1. Neleri test edelim?
- Spring, Hibernate gibi uygulama çatısından gelen kodlar test edilmez.
- Interface Respository lerinizi test etmeye gerek yoktur.
- private ve protected metodlar test edilmez.

## 2. Nelere Dikkat etmeliyim?
- Birim test yaparken sadece ilgili method un kendi görevlerini test edin.
- Birimin dışında kalan kodlar test edilmez. Mock kullanarak simüle edilir.
- Test yazarken değişken talep edilmez ve geri dönüş tipi yazılmaz. voild olarak kullanılır
- Test işlemi bir method un var olabilecek tüm senaryoları için test edilir.
- Bir method test edilecek ise, bu methodun bulunduğu 
  paket adı, sınıf adı birebir eşleşmelidir. sorunlara sebebiyet verebilirsiniz. 
- 

