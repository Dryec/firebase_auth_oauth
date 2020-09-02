package me.amryousef.apple.auth.firebase_apple_auth

import android.app.Activity
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar


/** FirebaseAppleAuthPlugin */
@Suppress("DEPRECATION")
class FirebaseAppleAuthPlugin : FlutterPlugin, ActivityAware, MethodCallHandler {

    private var activity: Activity? = null

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        val channel = MethodChannel(flutterPluginBinding.flutterEngine.dartExecutor, "me.amryousef.apple.auth/firebase_apple_auth")
        channel.setMethodCallHandler(this)
    }

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "me.amryousef.apple.auth/firebase_apple_auth")
            channel.setMethodCallHandler(FirebaseAppleAuthPlugin().apply { activity = registrar.activity() })
        }
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        result.notImplemented()
/*
        val providerId = call.argument<String>("provider")
        val scopes = call.argument<String>("scopes")
        val parameters = call.argument<String>("parameters")
        val linkWithCredential = call.argument<Boolean>("link_credential")

        when {
            providerId == null -> {
                result.error("InvalidProviderID", "provider can't be null", null)
            }
            scopes == null -> {
                result.error("InvalidScopes", "scopes can't be null", null)
            }
            linkWithCredential == null -> {
                result.error("InvalidLinkCredential", "link_credential can't be null", null)
            }
            else -> {
                val gson = Gson()
                val providerBuilder = OAuthProvider.newBuilder(providerId)
                providerBuilder.setScopes(gson.fromJson(scopes, object : TypeToken<List<String>>() {}.type))
                if (parameters != null) {
                    providerBuilder.addCustomParameters(
                            gson.fromJson<Map<String, String>>(
                                    parameters,
                                    object : TypeToken<Map<String, String>>() {}.type)
                    )
                }
                val provider = providerBuilder.build()

                activity?.let {
                    val auth = call.argument<String>("app")?.let { appName ->
                        FirebaseAuth.getInstance(FirebaseApp.getInstance(appName))
                    } ?: FirebaseAuth.getInstance()
                    val pending = auth.pendingAuthResult

                    @Suppress("IfThenToElvis")
                    if (pending != null) {
                        pending.addOnSuccessListener { authResult ->
                            (authResult.credential as? OAuthCredential)?.let { oAuthCredential ->
                                result.success("${oAuthCredential.accessToken}:${oAuthCredential.secret}")
                            } ?: result.success("")
                        }.addOnFailureListener { error ->
                            result.error((error as FirebaseAuthException).errorCode, error.localizedMessage, null)
                        }
                    } else {
                        auth.startActivityForSignInWithProvider(it, provider).addOnSuccessListener { authResult ->
                            (authResult.credential as? OAuthCredential)?.let(fun(oAuthCredential: OAuthCredential) {
                                result.success("${oAuthCredential.accessToken}:${oAuthCredential.secret}")
                            }) ?: result.success("")
                        }.addOnFailureListener { error ->
                            result.error((error as FirebaseAuthException).errorCode, error.localizedMessage, null)
                        }
                    }
                }
            }
        }

        activity?.let {
            val auth = call.argument<String>("app")?.let { appName ->
                FirebaseAuth.getInstance(FirebaseApp.getInstance(appName))
            } ?: FirebaseAuth.getInstance()
            val pending = auth.pendingAuthResult
            @Suppress("IfThenToElvis")
            if (pending != null) {
                pending.addOnSuccessListener { authResult ->
                    (authResult.credential as? OAuthCredential)?.let { oAuthCredential ->
                        result.success("${oAuthCredential.accessToken}:${oAuthCredential.secret}")
                    } ?: result.success("")
                }.addOnFailureListener { error ->
                    result.error((error as FirebaseAuthException).errorCode, error.localizedMessage, null)
                }
            } else {
                auth.startActivityForSignInWithProvider(it, provider).addOnSuccessListener { authResult ->
                    (authResult.credential as? OAuthCredential)?.let(fun(oAuthCredential: OAuthCredential) {
                        result.success("${oAuthCredential.accessToken}:${oAuthCredential.secret}")
                    }) ?: result.success("")
                }.addOnFailureListener { error ->
                    result.error((error as FirebaseAuthException).errorCode, error.localizedMessage, null)
                }
            }

        }*/
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }

    override fun onDetachedFromActivity() {
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        activity = binding.activity
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
    }

    override fun onDetachedFromActivityForConfigChanges() {
    }
}
