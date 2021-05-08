# Harcama Takip
## GDG - Android Bootcamp Turkey Bitirme Projesi

Google Developer Groups Turkey tarafından 22 Şubat 2021 - 23 Nisan 2021 tarihleri arasında düzenlenen Android Bootcamp eğitiminin bitirme projesidir.

**Harcama Takip**; kullanıcının harcamalarını takip edip, farklı para birimlerini içinde kullanacağı, kendi ismini ve toplam harcama miktarını uygulama içinde görüntüleyebilemesini sağlayan bir uygulamadır.

### Splash ve Onboarding Ekranı
Uygulama açıldığında 3 saniyelik bir splash screen'in ardından onboarding screen ile karşılaşılmaktadır.

<img src="images/splash.png" width="220" height="440"> <img src="images/os1.png" width="220" height="440"> 
<img src="images/os2.png" width="220" height="440"> <img src="images/os3.png" width="220" height="440">

### Ana Sayfa Ekranı
Uygulamada TL (Türk Lirası), GBP (İngiliz Sterlini), EUR (Euro), USD (Amerikan Doları) ve PLN (Polonya Zlotysi) olmak üzere 5 para birimi bulunmaktadır. Her para birimine tıklandığında harcamaların ilgili para birimine ait tutarı ve toplam harcaması güncel kur verileri ile gösterilmektedir.
Offline'da çalışabilmektedir.

<img src="images/anasayfa.png" width="225" height="450">

### İsim Değiştirme Ekranı
Anasayfa üzerinden "İsminizi Giriniz"e tıklayarak İsim Değiştir ekranına ulaşılmaktadır. Yapılan tercihe göre kullanıcıya ana sayfada farklı şekillerde hitap edilmektedir.

<img src="images/isimDegistir.png" width="225" height="450"> 

### Harcama Ekleme Ekranı
Ekle butonu yoluyla yeni harcama eklemesi yapılmaktadır. Buradaki her alanın doldurulması zorunludur.

<img src="images/harcamaEkle.png" width="225" height="450"> 

### Harcama Detay Ekranı
Ekleme sonrasında ana sayfada Recyclerview içerisindeki harcamaların detayları ayrı ayrı görüntülenebilmekte ve silinebilmektedir.

<img src="images/harcamaSilEuro.png" width="225" height="450">
