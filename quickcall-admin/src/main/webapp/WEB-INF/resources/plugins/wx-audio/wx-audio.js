var WxAudio = function (i) {
    var t = {};

    function e(o) {
        if (t[o]) return t[o].exports;
        var n = t[o] = {
            i: o,
            l: !1,
            exports: {}
        };
        return i[o].call(n.exports, n, n.exports, e), n.l = !0, n.exports
    }

    return e.m = i, e.c = t, e.d = function (i, t, o) {
        e.o(i, t) || Object.defineProperty(i, t, {
            configurable: !1,
            enumerable: !0,
            get: o
        })
    }, e.r = function (i) {
        Object.defineProperty(i, "__esModule", {
            value: !0
        })
    }, e.n = function (i) {
        var t = i && i.__esModule ? function () {
            return i.default
        } : function () {
            return i
        };
        return e.d(t, "a", t), t
    }, e.o = function (i, t) {
        return Object.prototype.hasOwnProperty.call(i, t)
    }, e.p = "", e(e.s = 2)
}([function (i, t, e) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var o = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (i) {
        return typeof i
    } : function (i) {
        return i && "function" == typeof Symbol && i.constructor === Symbol && i !== Symbol.prototype ? "symbol" : typeof i
    };
    (t.utils = {
        deviceVersion: function () {
            var i = navigator.userAgent;
            return {
                userAgent: i,
                appVersion: navigator.appVersion,
                trident: i.includes("Trident"),
                presto: i.includes("Presto"),
                webKit: i.includes("AppleWebKit"),
                gecko: i.includes("Gecko") && !i.includes("KHTML"),
                mobile: !!i.match(/AppleWebKit.*Mobile/i) || !!i.match(/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/),
                ios: !!i.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
                android: i.includes("Android") || i.includes("Linux"),
                iPhone: i.includes("iPhone") || i.includes("Mac"),
                iPad: i.includes("iPad"),
                webApp: !i.includes("Safari"),
                language: (navigator.browserLanguage || navigator.language).toLowerCase(),
                plugins: navigator.plugins,
                platform: navigator.platform,
                isOnline: navigator.onLine,
                geolocation: navigator.geolocation
            }
        },
        setConsole: function (i) {
            var t = Object.assign({
                text: "",
                isOneLine: 1,
                author: ""
            }, i);
        },
        initRem: function () {
            var i = {};
            i.resizeEvt = "orientationchange" in window ? "orientationchange" : "resize", i.Html = document.getElementsByTagName("html")[0], i.widthProportion = function () {
                var t = Number((document.body && document.body.clientWidth || i.Html.offsetWidth) / 750).toFixed(4);
                return (t = t > 515 / 750 ? .6867 : t) < 320 / 750 ? .4267 : t
            }, i.changePage = function () {
                i.Html.setAttribute("style", "font-size:" + 100 * i.widthProportion() + "px")
            }, i.changePage(), document.addEventListener && (window.addEventListener(i.resizeEvt, i.changePage, !1), document.addEventListener("DOMContentLoaded", i.changePage, !1))
        },
        initType: function () {
            this.dataType = {};
            var i = this;
            ["Null", "Undefined", "Object", "Array", "String", "Number", "Boolean", "Function", "RegExp"].forEach(function (t) {
                i.dataType["is" + t] = function (i) {
                    return function (i) {
                        return Object.prototype.toString.call(i).match(/\[object (.*?)\]/)[1].toLowerCase()
                    }(i) === t.toLowerCase()
                }
            })
        },
        getRandomEleFromArr: function (i, t) {
            for (var e = new Set(i), o = Array.from(e), n = new Array, r = 0; r < t; r++) {
                if (!(o.length > 0)) {
                    break
                }
                var a = Math.floor(Math.random() * o.length);
                n.push(o[a]), o.splice(a, 1)
            }
            return n
        },
        notification: function (i) {
            var t = this;
            return new Promise(function (i, o) {
                window.Notification && "denied" !== Notification.permission ? Notification.requestPermission(function (o) {
                    var n = new Notification(e.title, {
                        body: e.body,
                        icon: e.icon
                    });
                    n.onshow = function () {
                        t.setConsole({
                            text: "",
                            isOneLine: 0,
                            author: ""
                        })
                    }, n.onclick = function () {
                        i()
                    }
                }) : o()
            })
        },
        randomColor: function () {
            var i = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : 1;
            return "rgba(" + Math.floor(256 * Math.random()) + "," + Math.floor(256 * Math.random()) + "," + Math.floor(256 * Math.random()) + "," + i + ")"
        },
        showLayoutFramework: function () {
            [].forEach.call(document.querySelectorAll("*"), function (i) {
                i.style.outline = "1px solid #" + (~~(Math.random() * (1 << 24))).toString(16)
            })
        },
        getUrlParam: function (i) {
            for (var t = i.search.slice(1).split("&"), e = {}, o = 0; o < t.length; o++) e[t[o].split("=")[0]] = t[o].split("=")[1];
            return e
        },
        strLength: function (i) {
            for (var t = 0, e = 0; e < i.length; e++) {
                t += i.charCodeAt(e) > 255 ? 2 : 1
            }
            return t
        },
        extendDeep: function (i, t) {
            for (var e in t) t.hasOwnProperty(e) && ("object" === o(t[e]) ? this.extendDeep(i[e], t[e]) : i[e] = t[e]);
            return i
        }
    }).initType()
}, function (i, t, e) {
    "use strict";
    Object.defineProperty(t, "__esModule", {
        value: !0
    });
    var o = function () {
        function i(i, t) {
            for (var e = 0; e < t.length; e++) {
                var o = t[e];
                o.enumerable = o.enumerable || !1, o.configurable = !0, "value" in o && (o.writable = !0), Object.defineProperty(i, o.key, o)
            }
        }

        return function (t, e, o) {
            return e && i(t.prototype, e), o && i(t, o), t
        }
    }();
    e(4);
    var n = function () {
        function i(t) {
            !function (i, t) {
                if (!(i instanceof t)) throw new TypeError("Cannot call a class as a function")
            }(this, i);
            this.opt = Object.assign({}, {
                ele: null,
                width: "320px",
                title: null,
                src: "",
                disc: null,
                loop: !0,
                ended: function () {
                }
            }, t), "string" == typeof this.opt.ele && (this.opt.ele = document.querySelector(this.opt.ele)), this.opt.ele && (this.loading = !1, this.isDrag = !1, this.isplaying = !1, this.durationT = 0, this.currentT = 0, this.currentP = 0, this.maxProgressWidth = 0, this.dragProgressTo = 0, this.reduceTBefore = 0, this.reduceTAfter = 0, this.initDom())
        }

        return o(i, [{
            key: "initDom",
            value: function () {
                this.wxAudioC = document.createElement("div"), this.wxAudioC.className = "wx-audio-content", this.wxAudioC.style.width = this.opt.width, this.opt.ele.appendChild(this.wxAudioC), this.wxAudio = document.createElement("audio"), this.wxAudio.className = "wx-audio-content", this.wxAudio.src = this.opt.src, this.opt.loop && this.wxAudio.setAttribute("loop", this.opt.loop), this.wxAudioC.appendChild(this.wxAudio), this.wxAudioL = document.createElement("div"), this.wxAudioL.className = "wx-audio-left", this.wxAudioC.appendChild(this.wxAudioL), this.wxAudioStateImg = document.createElement("img"), this.wxAudioStateImg.className = "wx-audio-state", this.wxAudioStateImg.src = "resources/images/pause.png", this.wxAudioL.appendChild(this.wxAudioStateImg), this.wxAudioR = document.createElement("div"), this.wxAudioR.className = "wx-audio-right", this.wxAudioC.appendChild(this.wxAudioR), this.wxAudioT = document.createElement("p"), this.wxAudioR.appendChild(this.wxAudioT), this.wxAudioD = document.createElement("p"), this.wxAudioD.className = "wx-audio-disc", this.wxAudioD.innerText = this.opt.disc, this.wxAudioR.appendChild(this.wxAudioD), this.wxAudioP = document.createElement("div"), this.wxAudioP.className = "wx-audio-progrees", this.wxAudioR.appendChild(this.wxAudioP), this.wxAudioDetail = document.createElement("div"), this.wxAudioDetail.className = "wx-progrees-detail", this.wxAudioP.appendChild(this.wxAudioDetail), this.wxVoiceP = document.createElement("span"), this.wxVoiceP.className = "wx-voice-p", this.wxAudioDetail.appendChild(this.wxVoiceP), this.wxBufferP = document.createElement("span"), this.wxBufferP.className = "wx-buffer-p", this.wxAudioDetail.appendChild(this.wxBufferP), this.wxLoading = document.createElement("span"), this.wxLoading.className = "wx-loading", this.wxAudioDetail.appendChild(this.wxLoading), this.wxLoadingWrapper = document.createElement("span"), this.wxLoadingWrapper.className = "wx-loading-wrapper", this.wxLoading.appendChild(this.wxLoadingWrapper), this.wxAudioOrigin = document.createElement("div"), this.wxAudioOrigin.className = "wx-audio-origin", this.wxAudioP.appendChild(this.wxAudioOrigin), this.wxAudioTime = document.createElement("div"), this.wxAudioTime.className = "wx-audio-time", this.wxAudioR.appendChild(this.wxAudioTime), this.wxAudioCurrent = document.createElement("span"), this.wxAudioCurrent.className = "current-t", this.wxAudioCurrent.innerText = "00:00", this.wxAudioTime.appendChild(this.wxAudioCurrent), this.wxAudioDuration = document.createElement("span"), this.wxAudioDuration.className = "duration-t", this.wxAudioDuration.innerText = "00:00", this.wxAudioTime.appendChild(this.wxAudioDuration), this.initAudioEvent()
            }
        }, {
            key: "audioPlay",
            value: function () {
                this.wxAudio.play(), this.isPlaying = !0
            }
        }, {
            key: "audioPause",
            value: function () {
                this.wxAudio.pause(), this.isPlaying = !1
            }
        }, {
            key: "audioPlayPause",
            value: function () {
                this.isPlaying ? this.audioPause() : this.audioPlay()
            }
        }, {
            key: "audioCut",
            value: function (i, t, e) {
                this.wxAudio.src = i, this.wxAudioT.innerText = t, this.wxAudioD.innerText = e, this.durationT = 0, this.currentT = 0, this.currentP = 0, this.dragProgressTo = 0, this.wxAudioCurrent.innerText = "00:00", this.wxAudioOrigin.style.left = "0px", this.wxVoiceP.style.width = "0px", this.audioPlay()
            }
        }, {
            key: "showLoading",
            value: function (i) {
                this.loading = i || !1, this.loading ? this.wxLoading.style.display = "block" : this.wxLoading.style.display = "none"
            }
        }, {
            key: "initAudioEvent",
            value: function () {
                var i = this;
                i.wxAudio.onplaying = function () {
                    var t = new Date;
                    i.isPlaying = !0, i.reduceTBefore = Date.parse(t) - Math.floor(1e3 * i.wxAudio.currentTime), i.wxAudioStateImg.src = "resources/images/playing.gif"
                }, i.wxAudio.onpause = function () {
                    i.isPlaying = !1, i.showLoading(!1), i.wxAudioStateImg.src = "resources/images/pause.png"
                }, i.wxAudio.onloadedmetadata = function () {
                    i.durationT = i.wxAudio.duration, i.wxAudioDuration.innerText = i.formartTime(i.wxAudio.duration)
                }, i.wxAudio.onwaiting = function () {
                    i.wxAudio.paused || i.showLoading(!0)
                }, i.wxAudio.onprogress = function () {
                    if (i.wxAudio.buffered.length > 0) {
                        for (var t = 0, e = 0; e < i.wxAudio.buffered.length; e++) (t += i.wxAudio.buffered.end(e) - i.wxAudio.buffered.start(e)) > i.durationT && (t = i.durationT, i.showLoading(!1), console.log("缓冲完成"));
                        var o = Math.floor(t / i.durationT * 100);
                        i.wxBufferP.style.width = o + "%"
                    }
                    var n = new Date;
                    i.wxAudio.paused || (i.reduceTAfter = Date.parse(n) - Math.floor(1e3 * i.currentT), i.reduceTAfter - i.reduceTBefore > 1e3 ? i.showLoading(!0) : i.showLoading(!1))
                }, i.wxAudio.onended = function () {
                    i.opt.ended()
                }, i.wxAudio.ontimeupdate = function () {
                    var t = new Date;
                    i.isDrag || (i.currentT = i.wxAudio.currentTime, i.currentP = Number(i.wxAudio.currentTime / i.durationT * 100), i.reduceTBefore = Date.parse(t) - Math.floor(1e3 * i.currentT), i.currentP = i.currentP > 100 ? 100 : i.currentP, i.wxVoiceP.style.width = i.currentP + "%", i.wxAudioOrigin.style.left = i.currentP + "%", i.wxAudioCurrent.innerText = i.formartTime(i.wxAudio.currentTime), i.showLoading(!1))
                }, i.wxAudioStateImg.onclick = function () {
                    i.audioPlayPause()
                }, i.wxAudioOrigin.onmousedown = function (t) {
                    i.isDrag = !0;
                    var e = (t || window.event).clientX,
                        o = t.target.offsetLeft;
                    i.maxProgressWidth = i.wxAudioDetail.offsetWidth, i.wxAudioC.onmousemove = function (t) {
                        if (i.isDrag) {
                            var n = (t || window.event).clientX;
                            i.dragProgressTo = Math.min(i.maxProgressWidth, Math.max(0, o + (n - e))), i.updatePorgress()
                        }
                    }, i.wxAudioC.onmouseup = function () {
                        i.isDrag && (i.isDrag = !1, i.wxAudio.currentTime = Math.floor(i.dragProgressTo / i.maxProgressWidth * i.durationT))
                    }, i.wxAudioC.onmouseleave = function () {
                        i.isDrag && (i.isDrag = !1, i.wxAudio.currentTime = Math.floor(i.dragProgressTo / i.maxProgressWidth * i.durationT))
                    }
                }, i.wxAudioOrigin.ontouchstart = function (t) {
                    i.isDrag = !0;
                    var e = t || window.event,
                        o = e.touches[0].clientX,
                        n = e.target.offsetLeft;
                    i.maxProgressWidth = i.wxAudioDetail.offsetWidth, i.wxAudioC.ontouchmove = function (t) {
                        if (i.isDrag) {
                            var e = (t || window.event).touches[0].clientX;
                            i.dragProgressTo = Math.min(i.maxProgressWidth, Math.max(0, n + (e - o))), i.updatePorgress()
                        }
                    }, i.wxAudioC.ontouchend = function () {
                        i.isDrag && (i.isDrag = !1, i.wxAudio.currentTime = Math.floor(i.dragProgressTo / i.maxProgressWidth * i.durationT))
                    }
                }, i.wxAudioDetail.onclick = function (t) {
                    var e = (t || window.event).layerX,
                        o = i.wxAudioDetail.offsetWidth;
                    i.wxAudio.currentTime = Math.floor(e / o * i.durationT)
                }
            }
        }, {
            key: "updatePorgress",
            value: function () {
                this.wxAudioOrigin.style.left = this.dragProgressTo + "px", this.wxVoiceP.style.width = this.dragProgressTo + "px";
                var i = Math.floor(this.dragProgressTo / this.maxProgressWidth * this.durationT);
                this.wxAudioCurrent.innerText = this.formartTime(i)
            }
        }, {
            key: "formartTime",
            value: function (i) {
                var t = function (i) {
                        return (i = i.toString())[1] ? i : "0" + i
                    },
                    e = Math.floor(i / 60),
                    o = Math.floor(i % 60);
                return t(e) + ":" + t(o)
            }
        }]), i
    }();
    t.default = n
}, function (i, t, e) {
    "use strict";
    e(9);
    var o, n = e(1),
        r = (o = n) && o.__esModule ? o : {
            default: o
        };
    e(0);
    var a = new r.default({
        ele: ".wx-audio",
        disc: "",
        src: "",
        width: "320px",
        loop: !0
    });
    // a.audioPlay();
    // document.getElementById("play").onclick = function () {
    //     a.audioPlay()
    // }
    // , document.getElementById("pause").onclick = function () {
    //     a.audioPause()
    // },
    // document.getElementById("cutVoice").onclick = function () {
    //     var voiceUrl = $("#voiceUrl").val();
    //     console.log(voiceUrl);
    //     a.audioCut(voiceUrl, "ocean", "ocean22")
    // }
    audioCutFn = function (src) {
        a.audioCut(src, "", "")
    }
}, , function (i, t) {
}, , , , , function (i, t) {
}]).default;

var audioCutFn;