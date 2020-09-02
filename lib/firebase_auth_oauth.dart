library firebase_apple_auth;

import 'dart:convert';

import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

class FirebaseAuthOAuth {
  final FirebaseApp _app;

  static const MethodChannel _channel =
      const MethodChannel('me.amryousef.apple.auth/firebase_apple_auth');

  FirebaseAuthOAuth._({FirebaseApp app}) : _app = app ?? Firebase.app();

  factory FirebaseAuthOAuth() {
    return FirebaseAuthOAuth._();
  }

  Future<String> openSignInFlow(
      {@required String provider,
      List<String> scopes = const [],
      bool linkToCurrentUser = false,
      Map<String, String> customOAuthParameters}) async {
    return await _channel.invokeMethod("openSignInFlow", {
      'provider': provider,
      'app': _app.name,
      'scopes': json.encode(scopes),
      'link_credential': linkToCurrentUser,
      if (customOAuthParameters != null)
        'parameters': json.encode(customOAuthParameters)
    });
  }

  FirebaseAuthOAuth withApp(FirebaseApp app) => FirebaseAuthOAuth._(app: app);
}
