#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>

@interface RCT_EXTERN_MODULE(SafeArea, RCTEventEmitter)
RCT_EXPORT_BLOCKING_SYNCHRONOUS_METHOD(getActualSafeArea);
@end
