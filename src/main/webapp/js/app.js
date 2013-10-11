/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

$( document ).ready( function() {

    // Instantiate our authenticator
    var restAuth = AeroGear.Auth({
        name: "auth",
        settings: {
            endpoints: {
                login: "rest/auth/login",
                logout: "rest/auth/logout"
            }
        }
    }).modules.auth;

    var otpSecret = AeroGear.Pipeline({
            name: "secret",
            settings: {
                endpoint: "rest/auth/otp/secret",
                authenticator: restAuth
            }
    }).pipes.secret;

    var otp = AeroGear.Pipeline({
        name: "otp",
        settings: {
            endpoint: "rest/auth/otp",
            authenticator: restAuth
        }
    }).pipes.otp;


    $( "form" ).on( "submit", function( event ) {
        event.preventDefault();
        var form = $( this ),
            formType = form.attr( "name" ),
            data
        data = form.serializeObject();
        switch ( formType ) {
            case "login":
                restAuth.login(data, {
                    contentType: "application/json",
                    success: function( data ) {
                        $( "#logindiv" ).hide();
                        $( "#logoutdiv" ).show();
                        $( "#otpdiv" ).show();
                    }
                });
                break;
            case "otp":
                otp.save(data, {
                    contentType: "application/json",
                    success: function( data ) {
                        $( "#otpgooddiv" ).show();
                    },
                    error: function(data) {
                        alert("OTP invalid");
                    }
                })
        }
        });


    $(' #qrcode ').click( function( event ) {
        event.preventDefault();

        otpSecret.read({
            success:function (data) {
                console.log(data);
                $('#qrcode-div').empty().qrcode(data.uri);
            }
        });
	});

    $(' #logout ').click( function( event ) {
        event.preventDefault();
        restAuth.logout({
            contentType: "application/json",
            success: function( data ) {
                $( "#logindiv" ).show();
                $( "#logoutdiv" ).hide();
                $( "#otpdiv" ).hide();
            }
        });
    });

    // Serializes a form to a JavaScript Object
    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each( a, function() {
            if ( o[ this.name ] ) {
                if ( !o[ this.name ].push ) {
                    o[ this.name ] = [ o[ this.name ] ];
                }
                o[ this.name ].push( this.value || '' );
            } else {
                o[ this.name ] = this.value || '';
            }
        });
        return o;
    };

});
