import 'dart:async';

import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_auth_oauth/firebase_auth_oauth.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  Future<void> performSignIn() async {
    try {
      await FirebaseAuthOAuth()
          .openSignInFlow(provider: "apple.com");
    } on PlatformException {
      debugPrint("error logging in");
    }
  }
  Future<void> performSignInGoogle() async {
    try {
      await FirebaseAuthOAuth()
          .openSignInFlow(provider: "google.com");
    } on PlatformException {
      debugPrint("error logging in");
    }
  }
  Future<void> performSignInTwitter() async {
    try {
      await FirebaseAuthOAuth()
          .openSignInFlow(provider: "twitter.com");
    } on PlatformException {
      debugPrint("error logging in");
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          children: <Widget>[
            Center(
              child: Text('Running example'),
            ),
            Padding(
              padding: EdgeInsets.all(10),
              child: StreamBuilder(
                  initialData: null,
                  stream: FirebaseAuth.instance.onAuthStateChanged,
                  builder: (BuildContext context,
                      AsyncSnapshot<FirebaseUser> snapshot) {
                    return Column(
                      children: <Widget>[
                        RaisedButton(
                          onPressed: () async {
                            if (snapshot.data != null) {
                              await FirebaseAuth.instance.signOut();
                            } else {
                              await performSignIn();
                            }
                          },
                          child: Text(snapshot.data != null ? "Logout" : "Login"),
                        ),
                        RaisedButton(
                          onPressed: () async {
                            if (snapshot.data != null) {
                              await FirebaseAuth.instance.signOut();
                            } else {
                              await performSignInGoogle();
                            }
                          },
                          child: Text(snapshot.data != null ? "Logout" : "Login"),
                        ),
                        RaisedButton(
                          onPressed: () async {
                            if (snapshot.data != null) {
                              await FirebaseAuth.instance.signOut();
                            } else {
                              await performSignInTwitter();
                            }
                          },
                          child: Text(snapshot.data != null ? "Logout" : "Login"),
                        ),
                      ],
                    );
                  }),
            )
          ],
        ),
      ),
    );
  }
}
