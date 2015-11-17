# An example of using both UI Automator and Espresso

[Roppongi.aar #2](http://roppongi-aar.connpass.com/event/21907/)で紹介した、UI Automator 2とEspressoを、同一テストメソッド内で混ぜて使ったサンプルコードです。

テスト対象アプリの仕様と、テストコードの操作シナリオは
[Roppongi.aar #2の発表スライド](https://speakerdeck.com/sumio/ui-automator-becomes-friendly-with-espresso?slide=14)
を参照してください。

# 動作確認環境

Android 5.1の、以下の環境で動作確認しています。
言語設定を日本語にして動かしてください。

- エミュレータ
- Nexus7 (2013)
- Nexus7 (2012)

Android 4.4では、Settingsアプリのレイアウトヒエラルキーが5.1と異なるため、このままでは動きません。
ただし、UI Automator 2とEspressoを混ぜて使うこと自体は、Android 4.4でも同じ方法で実現できます。

# License

Copyright 2015 TOYAMA Sumio <<jun.nama@gmail.com>>  
Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
