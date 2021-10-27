import Foundation
import UIKIT
import React

@objc(SafeArea)
class SafeArea: RCTEventEmitter {

    override class func requiresMainQueueSetup() -> Bool { true }
    override func supportedEvents() -> [String]! { [] }

    override func constantsToExport() -> [AnyHashable : Any]! {
      let window = UIApplication.shared.windows.filter {$0.isKeyWindow}.first
      let topPadding = window?.rootViewController?.view.safeAreaInsets.top
      let bottomPadding = window?.rootViewController?.view.safeAreaInsets.bottom
      let statusBarHeight = UIApplication.shared.statusBarFrame.height
      return ["insetTop": topPadding ?? 0, "insetBottom": bottomPadding ?? 0, "statusBarHeight": statusBarHeight]
    }
}
