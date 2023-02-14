Specification Heading
=====================
Created by ugurcan on 12.02.2023

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
Paytr On Basvuru Senaryosu
----------------
tags: paytrOnBasvuruSenaryosu

* Suanki URL "https://www.paytr.com/" degerini iceriyor mu kontrol et
* "onBasvuruTexti" Elementine kadar kaydır
* "onBasvuruWebSitesiTextboxi" elementine "ugurcansoken.com" degeri yazilir
* "onBasvuruIsimSoyisimTextboxi" elementine "Uğurcan Söken" degeri yazilir
* "onBasvuruMailTextboxi" elementine "ugurcansoken@gmail.com" degeri yazilir
* "onBasvuruTelefonTextboxi" elementine "5448415144" degeri yazilir
* Elementine tıkla "onBasvuruIsletmeTuruBoxi"
* "onBasvuruIsletmeTuruListesi" listesindeki elementlerden rastgele biri tiklanir
* Elementine tıkla "onBasvuruBasvuruyuTamamlaButonu"
* "onBasvuruIkinciAdimTexti" elementi "Ön başvuruyu tamamlamak için lütfen aşağıdaki bilgileri doldurun." degerini iceriyor mu kontrol et
* "onBasvuruIkinciAdimIsletmeUnvaniTextboxi" elementine "Sökenler A.Ş" degeri yazilir
* "onBasvuruIkinciAdimVergiNumarasiTextboxi" elementine "1234451234512" degeri yazilir
* "onBasvuruIkinciAdimVergiDairesiTextboxi" elementine "Avcılar" degeri yazilir
* "onBasvuruIkinciAdimCiroBeklentisiTextboxi" elementine "25000" degeri yazilir
* Elementine tıkla "onBasvuruIkinciAdimBilgileriGonderButonu"
* "onBasvurunuzAlinmistirTexti" elementinin gorulmesi beklenir

Amazon Senaryosu
------
tags: amazonSenaryosu

* "https://www.amazon.com.tr/ref=nav_logo" adresine git
* Suanki URL "https://www.amazon.com.tr/ref=nav_logo" degerini iceriyor mu kontrol et
* "cereziKabulEtButonu" elementi varsa tıkla
* "searchbox" elementine "Macbook Pro" degeri yazilir
* Elementine tıkla "searchAraButonu"
* "aramaSonucuUrunListesi" elementinin gorulmesi beklenir
* Elementine tıkla "aramaSonucuUrunListesi"
* "urunDetaySayfasiKontrolu" elementinin gorulmesi beklenir
* Urunun "urununFiyati" fiyat bilgisini "fiyatBilgisi" olarak, "urununModelAdi" model adini "modelBilgisi" olarak,"urununCPUBilgisi" CPU modelini "CPUBilgisi" olarak kaydet ve bilgiyi iceren yeni bir dosya olustur
