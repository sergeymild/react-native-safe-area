import { NativeModules, Platform, StatusBar } from 'react-native';

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
  // @ts-ignore
  private constants: {insetTop: number; insetBottom: number; statusBarHeight: number} = {};
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

  async actualSafeArea(): Promise<{insetTop: number; insetBottom: number; statusBarHeight: number}> {
    if (Platform.OS === 'android') return {insetBottom: 0, insetTop: 0, statusBarHeight: 0};
    return SA.getActualSafeArea();
  }

  get androidTopInset(): number {
    if (Platform.OS === 'ios') return 0;
    return SA.getTopInset();
  }

  get androidBottomInset(): number {
    if (Platform.OS === 'ios') return 0;
    return SA.getBottomInset();
  }

  get statusBarHeight(): number {
    if (Platform.OS === 'android') return StatusBar.currentHeight ?? 0;
    return this.constants.statusBarHeight;
  }
}

export const safeArea = new SafeArea();
