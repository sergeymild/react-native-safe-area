import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-safe-area' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const SA = NativeModules.SafeArea
  ? NativeModules.SafeArea
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

class SafeArea {
  private constants: Record<string, any> = {};
  constructor() {
    this.constants = SA.getConstants();
  }

  get bottomSafeArea(): number {
    if (Platform.OS === 'android') return 16;
    return this.constants.insetBottom === 0 ? 16 : this.constants.insetBottom;
  }

  get topSafeArea(): number {
    if (Platform.OS === 'android') return 0;
    return this.constants.insetTop;
  }
}

export const safeArea = new SafeArea();
