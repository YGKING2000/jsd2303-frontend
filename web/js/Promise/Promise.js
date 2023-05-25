/*class Promise {
    constructor(executor) {
        this.PromiseState = 'pending';
        this.PromiseResult = undefined;
        this.callbacks = [];
        const resolve = data => {
            if (this.PromiseState !== 'pending') return;
            this.PromiseState = 'fulfilled';
            this.PromiseResult = data;
            this.callbacks.forEach(item => {
                item.onResolved();
            });
        };
        const reject = data => {
            if (this.PromiseState !== 'pending') return;
            this.PromiseState = 'rejected';
            this.PromiseResult = data;
            this.callbacks.forEach(item => {
                item.onRejected();
            });
        };
        try {
            executor(resolve, reject);
        } catch (e) {
            reject(e);
        }
    }

    then(onResolved, onRejected) {
        if (typeof onResolved !== 'function') {
            onResolved = value => value;
        }
        if (typeof onRejected !== 'function') {
            onRejected = reason => {
                throw reason;
            };
        }
        return new Promise((resolve, reject) => {
            const callback = funcType => {
                try {
                    let result = funcType(this.PromiseResult);
                    if (result instanceof Promise) {
                        result.then(v => {
                            resolve(v);
                        }, r => {
                            reject(r);
                        });
                    } else {
                        resolve(result);
                    }
                } catch (e) {
                    reject(e);
                }
            };
            if (this.PromiseState === 'fulfilled') {
                callback(onResolved);
            }
            if (this.PromiseState === 'rejected') {
                callback(onRejected);
            }
            if (this.PromiseState === 'pending') {
                this.callbacks.push({
                    onResolved: function () {
                        callback(onResolved);
                    },
                    onRejected: function () {
                        callback(onRejected);
                    }
                });
            }
        });
    }

    catch(onRejected) {
        this.then(undefined, onRejected);
    }

    static resolve(value) {
        return new Promise((resolve, reject) => {
            if (value instanceof Promise) {
                value.then(v => {
                    resolve(v);
                }, r => {
                    reject(r);
                });
            } else {
                resolve(value);
            }
        });
    }

    static reject(reason) {
        return new Promise((resolve, reject) => {
            reject(reason);
        });
    }

    static all(promises) {
        return new Promise((resolve, reject) => {
            let count = 0;
            const arr = [];
            promises.forEach((item, index) => {
                item.then(v => {
                    arr[index] = v;
                    count++;
                    if (count === promises.length) {
                        resolve(arr);
                    }
                }, r =>{
                    reject(r);
                });
            });
        });
    }

    static race(promises) {
        return new Promise((resolve, reject) => {
            promises.forEach(item => {
                item.then(v => {
                    resolve(v);
                }, r => {
                    reject(r);
                })
            });
        });
    }
}*/

class Promise {
    constructor(executor) {
        this.PromiseState = 'pending';
        this.PromiseResult = undefined;
        this.callbacks = [];
        const resolve = data => {
            if (this.PromiseState !== 'pending') return;
            this.PromiseState = 'fulfilled';
            this.PromiseResult = data;
            this.callbacks.forEach(item => {
                item.onResolved();
            });
        };
        const reject = data => {
            if (this.PromiseState !== 'pending') return;
            this.PromiseState = 'rejected';
            this.PromiseResult = data;
            this.callbacks.forEach(item => {
                item.onRejected();
            });
        };
        try {
            executor(resolve, reject);
        } catch (e) {
            reject(e);
        }
    }

    then(onResolved, onRejected) {
        if (typeof onResolved !== 'function') {
            onResolved = value => value;
        }
        if (typeof onRejected !== 'function') {
            onRejected = reason => {
                throw reason;
            };
        }
        return new Promise((resolve, reject) => {
            const callback = funcType => {
                try {
                    let result = funcType(this.PromiseResult);
                    if (result instanceof Promise) {
                        result.then(v => {
                            resolve(v);
                        }, r => {
                            reject(r);
                        });
                    } else {
                        resolve(result);
                    }
                } catch (e) {
                    reject(e);
                }
            };
            if (this.PromiseState === 'fulfilled') {
                callback(onResolved);
            }
            if (this.PromiseState === 'rejected') {
                callback(onRejected);
            }
            if (this.PromiseState === 'pending') {
                this.callbacks.push({
                    onResolved: function () {
                        callback(onResolved);
                    },
                    onRejected: function () {
                        callback(onRejected);
                    }
                });
            }
        });
    }

    catch(onRejected) {
        this.then(undefined, onRejected);
    }

    static resolve(value) {
        return new Promise((resolve, reject) => {
            if (value instanceof Promise) {
                value.then(v => {
                    resolve(v);
                }, r => {
                    reject(r);
                });
            } else {
                resolve(value);
            }
        });
    }

    static reject(reason) {
        return new Promise((resolve, reject) => {
            reject(reason);
        });
    }

    static all(promises) {
        let count = 0;
        const arr = [];
        return new Promise((resolve, reject) => {
            promises.forEach((item, index) => {
                item.then(v => {
                    count++;
                    arr[index] = v;
                    if (count === promises.length) {
                        resolve(arr);
                    }
                }, r => {
                    reject(r);
                });
            });
        });
    }

    static race(promises) {
        return new Promise((resolve, reject) => {
            promises.forEach(item => {
                item.then(v => {
                    resolve(v);
                }, r => {
                    reject(r);
                });
            });
        });
    }
}























